package com.example.test.service;

import com.example.test.bean.SubTaskBean;

import java.util.Date;
import java.util.List;

public interface SubTaskService {
    /*外包任务*/
    String outSourcingApply(String ApplicantID,String HelpersID,String subTaskId);//申请外包
    String outSourcingHandover(String ApplicantID, String HelpersID, String subTaskId, Date taskOutSourceEndTime);//外包信息交接
    String outSourcingRecovery(String ApplicantID,String HelpersID,String subTaskId);//外包回收
    /*完成子任务*/
    //boolean subTaskExecute(String subTaskID);//提交文件
    String subTaskCompleteApply(String subTaskID);//申请完成子任务
    String subTaskCompleteConclusion(String subTaskID);//接受子任务完成（将子任务状态置为已完成）
    String subTaskCompleteRejection(String subTaskID);//拒绝子任务完成（将子任务置为未完成）

    //判断任务节点图中前面的节点是否已经全部完成
    boolean judgeBeforeAllTaskHasDone(String subTaskId);

    //获取所有前置任务节点
    List<String> getBeforeTaskId(String subTaskId);
}
