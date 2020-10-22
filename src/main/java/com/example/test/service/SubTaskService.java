package com.example.test.service;

public interface SubTaskService {
    /*外包任务*/
    boolean outSourcingApply(String ApplicantID,String HelpersID,String subTaskId);//申请外包
    boolean outSourcingHandover(String ApplicantID,String HelpersID,String subTaskId);//外包信息交接
    boolean outSourcingRecovery(String ApplicantID,String HelpersID,String subTaskId);//外包回收
    /*完成子任务*/
    //boolean subTaskExecute(String subTaskID);//提交文
    boolean subTaskCompleteApply(String subTaskID);//申请完成子任务
    boolean subTaskCompleteConclusion(String subTaskID);//接受子任务完成（将子任务状态置为已完成）
    boolean subTaskCompleteRejection(String subTaskID);//拒绝子任务完成（将子任务置为未完成）
}
