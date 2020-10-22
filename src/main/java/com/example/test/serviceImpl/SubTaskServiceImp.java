package com.example.test.serviceImpl;

import com.example.test.bean.SubTaskBean;
import com.example.test.bean.TaskFinishInfoBean;
import com.example.test.mapper.SubTaskMapper;
import com.example.test.mapper.TaskFinishInfoMapper;
import com.example.test.service.SubTaskService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubTaskServiceImp implements SubTaskService {

    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private TaskFinishInfoMapper taskFinishInfoMapper;

    @Override
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
    public String outSourcingHandover(String ApplicantID, String HelpersID, String subTaskId) {
        if (!outSourcingApply(ApplicantID, HelpersID, subTaskId).equals(ServiceUtil.SUCCESS)) {
            return outSourcingApply(ApplicantID, HelpersID, subTaskId);
        }
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(subTaskId);

        return null;
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
