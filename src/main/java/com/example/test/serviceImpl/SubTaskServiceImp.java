package com.example.test.serviceImpl;

import com.example.test.bean.*;
import com.example.test.mapper.*;
import com.example.test.service.NotifyService;
import com.example.test.service.SubTaskService;
import com.example.test.util.NotifyUtil;
import com.example.test.util.ProjectUtil;
import com.example.test.util.ServiceUtil;
import com.example.test.util.SubTaskUtil;
import com.example.test.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class SubTaskServiceImp implements SubTaskService {

    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private TaskFinishInfoMapper taskFinishInfoMapper;
    @Autowired
    private ProFinishInfoMapper proFinishInfoMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskNextMapper taskNextMapper;
    @Autowired
    private NotifyService notifyService;

    @Override
    //申请外包：判断有无申请者，判断
    public String outSourcingApply(String ApplicantID, String HelpersID, String subTaskId, Date taskOutSourceEndTime) {
        System.out.println("outSourcingApply");
        List<TaskFinishInfoBean> taskFinishInfoBeanApplicant = taskFinishInfoMapper.getSubTaskInfo(subTaskId, ApplicantID);
        System.out.println(taskFinishInfoBeanApplicant.size() + " size");
        if (taskFinishInfoBeanApplicant.size() == 0) { //没有申请者
            return ServiceUtil.FAILURE+"无此申请者用户";
        }
        EmployeeBean employeeBean = employeeMapper.getEmpInfoByEmpId(HelpersID);
//        List<TaskFinishInfoBean> taskFinishInfoBeanHelper = taskFinishInfoMapper.getSubTaskInfo(subTaskId, HelpersID);
        if (employeeBean == null) {//没有外包者
            return ServiceUtil.FAILURE+"无此外包者用户";
        }
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskId);
        if (subTaskBean.getSubTaskCanOutSource() == 0) { //不能外包
            return ServiceUtil.FAILURE+"此任务不允许外包，请自己一个人努力完成啊~";
        }
        if (subTaskBean.getSubTaskState() == SubTaskUtil.getTaskState(SubTaskUtil.TASK_STATE.HAS_FINISH)) {
            return ServiceUtil.FAILURE + "此任务已完成";
        }
//        EmployeeBean employeeBean = employeeMapper.getEmpInfoByEmpId(HelpersID);
        if (!employeeBean.hasSkill(subTaskBean.getSubTaskSkillType())) {
            return ServiceUtil.FAILURE + "该员工没有这个任务所需的技能";
        }
        return ServiceUtil.SUCCESS;
    }

    @Override
    //外包信息交接
    public String outSourcingHandover(String ApplicantID, String HelpersID, String subTaskId, Date taskOutSourceEndTime) {
        if (!outSourcingApply(ApplicantID, HelpersID, subTaskId, taskOutSourceEndTime).equals(ServiceUtil.SUCCESS)) {
            return outSourcingApply(ApplicantID, HelpersID, subTaskId, taskOutSourceEndTime);
        }
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskId);
        System.out.println("test id " + subTaskBean.getSubTaskId());
        System.out.println("test state " + subTaskBean.getSubTaskState());
        System.out.println("test out source " + subTaskBean.getSubTaskCanOutSource());
        System.out.println("test desc " + subTaskBean.getSubTaskDesc());
        System.out.println("test name " + subTaskBean.getSubTaskName());
        System.out.println("test skill type " + subTaskBean.getSubTaskSkillType());
        System.out.println("test finish count " + subTaskBean.getHasFinishFileCount());
        System.out.println("test total count " + subTaskBean.getTotalFileCount());
        System.out.println("test end time" + subTaskBean.getSubTaskEndTime());
        System.out.println("test id " + subTaskBean.getSubTaskInProject());
        TaskFinishInfoBean taskFinishInfoBean = new TaskFinishInfoBean();
        taskFinishInfoBean.setEmpId(HelpersID);
        taskFinishInfoBean.setSubTaskId(subTaskId);
        taskFinishInfoBean.setProjectId(subTaskBean.getSubTaskInProject());
        taskFinishInfoBean.setSubTaskOutSourceEndTime(taskOutSourceEndTime);
        taskFinishInfoBean.setDoType(SubTaskUtil.getTaskDoType(SubTaskUtil.DO_TYPE.OUT_SOURCE));
        taskFinishInfoMapper.insertTaskFinishInfo(taskFinishInfoBean);
        ProFinishInfoBean proFinishInfoBean = new ProFinishInfoBean();
        proFinishInfoBean.setEmpId(HelpersID);
        proFinishInfoBean.setProjectId(subTaskBean.getSubTaskInProject());
        proFinishInfoBean.setEmpPosition(ProjectUtil.getTaskDoType(ProjectUtil.EMP_POSITION.OUT_SOURCE_EMP));
        proFinishInfoMapper.insertProjectInfo(proFinishInfoBean);
        SubTaskBean subTaskBeanUpdated = new SubTaskBean();
        subTaskBeanUpdated.setSubTaskId(subTaskBean.getSubTaskId());
        subTaskBeanUpdated.setSubTaskName(subTaskBean.getSubTaskName());
        subTaskBeanUpdated.setSubTaskDesc(subTaskBean.getSubTaskDesc());
        subTaskBeanUpdated.setSubTaskCanOutSource(subTaskBean.getSubTaskCanOutSource());
        subTaskBeanUpdated.setSubTaskSkillType(subTaskBean.getSubTaskSkillType());
        subTaskBeanUpdated.setSubTaskInProject(subTaskBean.getSubTaskInProject());
        subTaskBeanUpdated.setSubTaskState(SubTaskUtil.getTaskState(SubTaskUtil.TASK_STATE.OUT_SOURCE));
        subTaskBeanUpdated.setSubTaskStartTime(subTaskBean.getSubTaskStartTime());
        subTaskBeanUpdated.setSubTaskEndTime(subTaskBean.getSubTaskEndTime());
        subTaskBean.setTotalFileCount(subTaskBean.getTotalFileCount());
        subTaskBean.setHasFinishFileCount(subTaskBean.getHasFinishFileCount());
        subTaskMapper.updateSubTask(subTaskBean);
        return ServiceUtil.SUCCESS;
    }

    /**
     * @param ApplicantID 申请外包员工ID
     * @param HelpersID 外包任务员工ID
     * @param subTaskId 任务ID
     * @return 成功
     */
    @Override
    public String outSourcingRecovery(String ApplicantID, String HelpersID, String subTaskId) {
        TaskFinishInfoBean taskFinishInfoBean = taskFinishInfoMapper.getSubTaskInfoByTaskIdEmpIdDoType(
                subTaskId, HelpersID, SubTaskUtil.getTaskDoType(SubTaskUtil.DO_TYPE.DO_BY_SELF));
        TaskFinishInfoBean taskFinishInfoBeanUpdate = new TaskFinishInfoBean();
        if (taskFinishInfoBean.getEmpId() == null) {
            System.out.println("null");
        }
        taskFinishInfoBeanUpdate.setEmpId(taskFinishInfoBean.getEmpId());
        taskFinishInfoBeanUpdate.setSubTaskId(taskFinishInfoBean.getSubTaskId());
        taskFinishInfoBeanUpdate.setProjectId(taskFinishInfoBean.getProjectId());
        taskFinishInfoBeanUpdate.setDoType(SubTaskUtil.getTaskDoType(SubTaskUtil.DO_TYPE.OUT_SOURCE_END));
        taskFinishInfoBeanUpdate.setSubTaskOutSourceEndTime(taskFinishInfoBean.getSubTaskOutSourceEndTime());
        taskFinishInfoMapper.updateTaskFinishInfo(taskFinishInfoBeanUpdate);
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskId);
        SubTaskBean subTaskBeanUpdated = new SubTaskBean();
        subTaskBeanUpdated.setSubTaskId(subTaskBean.getSubTaskId());
        subTaskBeanUpdated.setSubTaskName(subTaskBean.getSubTaskName());
        subTaskBeanUpdated.setSubTaskDesc(subTaskBean.getSubTaskDesc());
        subTaskBeanUpdated.setSubTaskCanOutSource(subTaskBean.getSubTaskCanOutSource());
        subTaskBeanUpdated.setSubTaskSkillType(subTaskBean.getSubTaskSkillType());
        subTaskBeanUpdated.setSubTaskInProject(subTaskBean.getSubTaskInProject());
        subTaskBeanUpdated.setSubTaskState(SubTaskUtil.getTaskState(SubTaskUtil.TASK_STATE.UNDONE));
        subTaskBeanUpdated.setSubTaskStartTime(subTaskBean.getSubTaskStartTime());
        subTaskBeanUpdated.setSubTaskEndTime(subTaskBean.getSubTaskEndTime());
        subTaskBean.setTotalFileCount(subTaskBean.getTotalFileCount());
        subTaskBean.setHasFinishFileCount(subTaskBean.getHasFinishFileCount());
        subTaskMapper.updateSubTask(subTaskBean);
        return ServiceUtil.SUCCESS;
    }

    @Override
    public String subTaskCompleteApply(String subTaskID) {
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskID);
        SubTaskBean subTaskBeanUpdated = new SubTaskBean();
        subTaskBeanUpdated.setSubTaskId(subTaskBean.getSubTaskId());
        subTaskBeanUpdated.setSubTaskName(subTaskBean.getSubTaskName());
        subTaskBeanUpdated.setSubTaskDesc(subTaskBean.getSubTaskDesc());
        subTaskBeanUpdated.setSubTaskCanOutSource(subTaskBean.getSubTaskCanOutSource());
        subTaskBeanUpdated.setSubTaskSkillType(subTaskBean.getSubTaskSkillType());
        subTaskBeanUpdated.setSubTaskInProject(subTaskBean.getSubTaskInProject());
        subTaskBeanUpdated.setSubTaskState(SubTaskUtil.getTaskState(SubTaskUtil.TASK_STATE.TO_BE_CHECKED));
        subTaskBeanUpdated.setSubTaskStartTime(subTaskBean.getSubTaskStartTime());
        subTaskBeanUpdated.setSubTaskEndTime(subTaskBean.getSubTaskEndTime());
        subTaskBean.setTotalFileCount(subTaskBean.getTotalFileCount());
        subTaskBean.setHasFinishFileCount(subTaskBean.getHasFinishFileCount());
        subTaskMapper.updateSubTask(subTaskBean);
        List<SubTaskBean> beans=subTaskMapper.getNextTaskListByTaskId(subTaskID);
        int result;
        for(SubTaskBean bean:beans){
            if(this.judgeBeforeAllTaskHasDone(bean.getSubTaskId())){
                bean.setSubTaskState(SubTaskUtil.TASK_STATE.UNDONE.ordinal());
                result = subTaskMapper.updateSubTask(bean);
                if(result!=1){
                    return ServiceUtil.FAILURE+"向数据库更新子任务失败";
                }
                EmployeeBean employeeBean=employeeMapper.getEmpInfoByTaskIdDoSelf(bean.getSubTaskId());
                if(employeeBean!=null) {
                    notifyService.addNotify(employeeBean.getEmpId(),null,"子任务"+bean.getSubTaskId()+"可以执行", NotifyUtil.NO_REPLY);
                    try {
                        WebSocketServer.sendInfo("updateNotify",bean.getSubTaskId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ServiceUtil.SUCCESS;
    }

    @Override
    public String subTaskCompleteConclusion(String subTaskID) {
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskID);
        SubTaskBean subTaskBeanUpdated = new SubTaskBean();
        subTaskBeanUpdated.setSubTaskId(subTaskBean.getSubTaskId());
        subTaskBeanUpdated.setSubTaskName(subTaskBean.getSubTaskName());
        subTaskBeanUpdated.setSubTaskDesc(subTaskBean.getSubTaskDesc());
        subTaskBeanUpdated.setSubTaskCanOutSource(subTaskBean.getSubTaskCanOutSource());
        subTaskBeanUpdated.setSubTaskSkillType(subTaskBean.getSubTaskSkillType());
        subTaskBeanUpdated.setSubTaskInProject(subTaskBean.getSubTaskInProject());
        subTaskBeanUpdated.setSubTaskState(SubTaskUtil.getTaskState(SubTaskUtil.TASK_STATE.HAS_FINISH));
        subTaskBeanUpdated.setSubTaskStartTime(subTaskBean.getSubTaskStartTime());
        subTaskBeanUpdated.setSubTaskEndTime(subTaskBean.getSubTaskEndTime());
        subTaskBean.setTotalFileCount(subTaskBean.getTotalFileCount());
        subTaskBean.setHasFinishFileCount(subTaskBean.getHasFinishFileCount());
        subTaskMapper.updateSubTask(subTaskBeanUpdated);
        return ServiceUtil.SUCCESS;
    }

    @Override
    public String subTaskCompleteRejection(String subTaskID) {
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskID);
        SubTaskBean subTaskBeanUpdated = new SubTaskBean();
        subTaskBeanUpdated.setSubTaskId(subTaskBean.getSubTaskId());
        subTaskBeanUpdated.setSubTaskName(subTaskBean.getSubTaskName());
        subTaskBeanUpdated.setSubTaskDesc(subTaskBean.getSubTaskDesc());
        subTaskBeanUpdated.setSubTaskCanOutSource(subTaskBean.getSubTaskCanOutSource());
        subTaskBeanUpdated.setSubTaskSkillType(subTaskBean.getSubTaskSkillType());
        subTaskBeanUpdated.setSubTaskInProject(subTaskBean.getSubTaskInProject());
        subTaskBeanUpdated.setSubTaskState(SubTaskUtil.getTaskState(SubTaskUtil.TASK_STATE.UNDONE));
        subTaskBeanUpdated.setSubTaskStartTime(subTaskBean.getSubTaskStartTime());
        subTaskBeanUpdated.setSubTaskEndTime(subTaskBean.getSubTaskEndTime());
        subTaskBean.setTotalFileCount(subTaskBean.getTotalFileCount());
        subTaskBean.setHasFinishFileCount(subTaskBean.getHasFinishFileCount());
        subTaskMapper.updateSubTask(subTaskBeanUpdated);
        return ServiceUtil.SUCCESS;
    }

    @Override
    public boolean judgeBeforeAllTaskHasDone(String subTaskId) {
        List<TaskNextBean> list = taskNextMapper.getBeforeTaskId(subTaskId);
        for (TaskNextBean taskNextBean : list) {
            String str = taskNextBean.getSubTaskId();
            SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(str);
            if (subTaskBean.getSubTaskState() != SubTaskUtil.getTaskState(SubTaskUtil.TASK_STATE.HAS_FINISH)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getBeforeTaskId(String subTaskId) {
        List<TaskNextBean> list = taskNextMapper.getBeforeTaskId(subTaskId);
        List<String> stringList = new ArrayList<>();
        for (TaskNextBean taskNextBean : list) {
            stringList.add(taskNextBean.getSubTaskId());
        }
        return stringList;
    }


}
