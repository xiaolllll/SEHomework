package com.example.test.serviceImpl;

import com.example.test.bean.ProFinishInfoBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.bean.TaskFinishInfoBean;
import com.example.test.mapper.ProFinishInfoMapper;
import com.example.test.mapper.SubTaskMapper;
import com.example.test.mapper.TaskFinishInfoMapper;
import com.example.test.service.SubTaskService;
import com.example.test.util.ProjectUtil;
import com.example.test.util.ServiceUtil;
import com.example.test.util.SubTaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    //申请外包：判断有无申请者，判断
    public String outSourcingApply(String ApplicantID, String HelpersID, String subTaskId) {
        List<TaskFinishInfoBean> taskFinishInfoBeanApplicant = taskFinishInfoMapper.getSubTaskInfo(subTaskId, ApplicantID);
        if (taskFinishInfoBeanApplicant.size() == 0) { //没有申请者
            return ServiceUtil.FAILURE+"无此申请者用户";
        }
        List<TaskFinishInfoBean> taskFinishInfoBeanHelper = taskFinishInfoMapper.getSubTaskInfo(subTaskId, HelpersID);
        if (taskFinishInfoBeanHelper.size() == 0) {//没有外包者
            return ServiceUtil.FAILURE+"无此外包者用户";
        }
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskId);
        if (subTaskBean.getSubTaskCanOutSource() == 0) { //不能外包
            return ServiceUtil.FAILURE+"此任务不允许外包，请自己一个人努力完成啊~";
        }
        return ServiceUtil.SUCCESS;
    }

    @Override
    //外包信息交接
    public String outSourcingHandover(String ApplicantID, String HelpersID, String subTaskId, Date taskOutSourceEndTime) {
        if (!outSourcingApply(ApplicantID, HelpersID, subTaskId).equals(ServiceUtil.SUCCESS)) {
            return outSourcingApply(ApplicantID, HelpersID, subTaskId);
        }
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskId);
        TaskFinishInfoBean taskFinishInfoBean = new TaskFinishInfoBean();
        taskFinishInfoBean.setEmpId(HelpersID);
        taskFinishInfoBean.setSubTaskId(subTaskId);
        taskFinishInfoBean.setProjectId(subTaskBean.getSubTaskInProjectId());
        taskFinishInfoBean.setSubTaskOutSourceEndTime(taskOutSourceEndTime);
        taskFinishInfoBean.setDoType(SubTaskUtil.getTaskDoType(SubTaskUtil.DO_TYPE.OUT_SOURCE));
        taskFinishInfoMapper.insertTaskFinishInfo(taskFinishInfoBean);
        ProFinishInfoBean proFinishInfoBean = new ProFinishInfoBean();
        proFinishInfoBean.setEmpId(HelpersID);
        proFinishInfoBean.setProjectId(subTaskBean.getSubTaskInProjectId());
        proFinishInfoBean.setEmpPosition(ProjectUtil.getTaskDoType(ProjectUtil.EMP_POSITION.OUT_SOURCE_EMP));
        proFinishInfoMapper.insertProjectInfo(proFinishInfoBean);
        return ServiceUtil.SUCCESS;
    }

    /**
     * 外包回收：删除外包员工ID所对应的记录
     * @param ApplicantID 申请外包员工ID
     * @param HelpersID 外包任务员工ID
     * @param subTaskId 任务ID
     * @return 成功
     */
    @Override
    public String outSourcingRecovery(String ApplicantID, String HelpersID, String subTaskId) {

        return null;
    }

    @Override
    public String subTaskCompleteApply(String subTaskID) {
        return null;
    }

    @Override
    public String subTaskCompleteConclusion(String subTaskID) {
        return null;
    }

    @Override
    public String subTaskCompleteRejection(String subTaskID) {
        return null;
    }
}
