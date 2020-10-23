package com.example.test.serviceImpl;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.ProjectBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.bean.TaskFinishInfoBean;
import com.example.test.component.DocumentManager;
import com.example.test.component.OIDGenerator;
import com.example.test.mapper.EmployeeMapper;
import com.example.test.mapper.SubTaskMapper;
import com.example.test.mapper.TaskFinishInfoMapper;
import com.example.test.service.ProjectService;
import com.example.test.util.ServiceUtil;
import com.example.test.util.SubTaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskFinishInfoMapper taskFinishInfoMapper;
    @Autowired
    private SubTaskServiceImp subTaskServiceImp;
    @Override
    public String addSubTask(SubTaskBean taskBean, ArrayList<String> leadingPath, ArrayList<String> succeedingPath, boolean isChain) {
        for (String s:leadingPath){
            if(subTaskMapper.getTaskInfoByProId(s)==null){
                return ServiceUtil.FAILURE+"数据库中未找到编号为"+s+"的前置任务";
            }
        }

        for (String s:succeedingPath){
            if(subTaskMapper.getTaskInfoByProId(s)==null){
                return ServiceUtil.FAILURE+"数据库中未找到编号为"+s+"的后继任务";
            }
        }

        taskBean.setSubTaskId(OIDGenerator.getInstance().createSubTaskID());
        taskBean.setSubTaskStartTime(new Date());

        int result = subTaskMapper.insertSubTask(taskBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"向数据库插入新的子任务失败";
        }

        /**
         * 没写完需要taskNextBean的插入函数
         */

        DocumentManager documentManager = new DocumentManager();
        if(!documentManager.createSubTaskFolder(taskBean.getSubTaskInProjectId(),taskBean.getSubTaskId())){
            return ServiceUtil.FAILURE+"创建子文件夹失败";
        }

        if(isChain){
            for (String s:succeedingPath){
                String temp=restartSubTask(s,true);
                if(temp.contains(ServiceUtil.FAILURE)){
                    return ServiceUtil.FAILURE+"子任务"+s+"重启失败。"+"失败信息："+temp;
                }
            }
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String deleteSubTask(String SubTaskID) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }
        /**
         * 没写完需要查询任务的负责人和外包人员
        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.OUT_SOURCE.ordinal()){
            EmployeeBean applicant = employeeMapper.
            String applicantID =
            subTaskServiceImp.outSourcingRecovery()
        }**/
        /**
         * 没写完想要连锁删除
         */
        int result = subTaskMapper.deleteSubTask(SubTaskID);
        if(result!=1){
            return ServiceUtil.FAILURE+"从数据库中删除子任务失败";
        }

        DocumentManager documentManager = new DocumentManager();
        if(!documentManager.deleteSubTaskFolder(subTaskBean.getSubTaskInProjectId(),subTaskBean.getSubTaskId())){
            return ServiceUtil.FAILURE+"删除子文件夹失败";
        }
        return ServiceUtil.SUCCESS;
    }

    @Override
    public String modifySubTask(SubTaskBean taskBean) {
        String SubTaskID = taskBean.getSubTaskId();
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }
        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.TO_BE_CHECKED.ordinal()||
        subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal()){
            return ServiceUtil.FAILURE+"编号为"+SubTaskID+"的任务处于不可修改的状态";
        }
        int result = subTaskMapper.updateSubTask(taskBean);
        if (result!=1){
            return ServiceUtil.FAILURE+"数据库中更新子任务失败";
        }
        return ServiceUtil.SUCCESS;
    }

    @Override
    public String restartSubTask(String SubTaskID, boolean isChain) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        if(subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal()){
            subTaskBean.setSubTaskState(SubTaskUtil.TASK_STATE.UNDONE.ordinal());
            int result=subTaskMapper.updateSubTask(subTaskBean);
            if(result!=1){
                return ServiceUtil.FAILURE+"数据库更新子任务状态失败";
            }
        }

        if(isChain){
            List<SubTaskBean> subTaskBeans= subTaskMapper.getNextTaskListByTaskId(SubTaskID);
            for (SubTaskBean s:subTaskBeans){
                String temp=restartSubTask(s.getSubTaskId(),true);
                if(temp.contains(ServiceUtil.FAILURE)){
                    return ServiceUtil.FAILURE+"子任务"+s.getSubTaskId()+"重启失败。"+"失败信息："+temp;
                }
            }
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String forceCompleteSubTask(String SubTaskID) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        /**
         * 没写完需要查询任务的负责人和外包人员
         if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.OUT_SOURCE.ordinal()){
         EmployeeBean applicant = employeeMapper.
         String applicantID =
         subTaskServiceImp.outSourcingRecovery()
         }**/

        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.TO_BE_CHECKED.ordinal()||
                subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.UNDONE.ordinal()){
                subTaskBean.setSubTaskState(SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal());
                int result = subTaskMapper.updateSubTask(subTaskBean);
                if(result!=1){
                    return ServiceUtil.FAILURE+"数据库更新子任务状态失败";
                }
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String setSubTaskPerson(String SubTaskID, String empID) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        EmployeeBean employeeBean=employeeMapper.getEmpInfoByEmpId(empID);
        if(employeeBean==null){
            return ServiceUtil.FAILURE+"未找到标号为"+empID+"的员工";
        }

        if (!employeeBean.hasSkill(subTaskBean.getSubTaskSkillType())) {
            return ServiceUtil.FAILURE + "该员工没有这个任务所需的技能";
        }

        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.TO_BE_CHECKED.ordinal()||
                subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal()){
            return ServiceUtil.FAILURE + "当前子任务不能更改人员";
        }

        /**
         * 没写完需要查询任务的负责人和外包人员
         if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.OUT_SOURCE.ordinal()){
         EmployeeBean applicant = employeeMapper.
         String applicantID =
         subTaskServiceImp.outSourcingRecovery()
         }**/

        /**
         * 没写完需要查询人物的负责人员
         */

        TaskFinishInfoBean taskFinishInfoBean = new TaskFinishInfoBean();
        taskFinishInfoBean.setEmpId(employeeBean.getEmpId());
        taskFinishInfoBean.setSubTaskId(subTaskBean.getSubTaskId());
        taskFinishInfoBean.setProjectId(subTaskBean.getSubTaskInProjectId());
        taskFinishInfoBean.setDoType(SubTaskUtil.DO_TYPE.DO_BY_SELF.ordinal());

        int result = taskFinishInfoMapper.insertTaskFinishInfo(taskFinishInfoBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库写入新的员工参与信息失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String projectCompleteApply(String projectID) {
        return null;
    }

    @Override
    public String createProject(ProjectBean projectBean) {
        return null;
    }

    @Override
    public String storageProject(String projectID) {
        return null;
    }

    @Override
    public String restartProject(String projectID) {
        return null;
    }

    @Override
    public String abandonProject(String projectID) {
        return null;
    }

    @Override
    public String completeProject(String projectID) {
        return null;
    }

    @Override
    public String modifyProject(String projectID) {
        return null;
    }

    @Override
    public String setProjectManager(String projectID, String empID) {
        return null;
    }

    @Override
    public String addProjectPerson(String projectID, String empID) {
        return null;
    }

    @Override
    public String deleteProjectPerson(String projectID, String empID) {
        return null;
    }
}
