package com.example.test.service;

import com.example.test.bean.*;


import java.util.ArrayList;
import java.util.List;

public interface DataQueryService {
    /*员工信息查询*/
    EmployeeBean getEmployee(String empID);//查看单个员工信息
    List<EmployeeBean> getAllEmployee();//查看所有员get工信息
    List<EmployeeBean> getSkillEmployee(int skillType);//查看具有技能的员工信息
    List<EmployeeBean> getProjectEmployee(String projectID);//查看一个项目的员工信息
    EmployeeBean getSubTaskEmployeeDoSelf(String subTaskId);//查看一个子任务的员工信息
    EmployeeBean getOutSourceTaskEmployee(String subTaskID);//查看一个子任务的外包员工信息
    List<EmployeeBean> getManagerInfo(); //查看所有管理员

    /*项目信息查询*/
    ProjectBean getProject(String projectID);//查看单个项目时的信息
    List<ProjectBean> getAllProject();//查看所有项目信息
    List<ProjectBean> getEmpProject(String empID);//查看一个员工参加的所有子项目信息
    List<ProjectBean> getRunProject(String empID);//查看员工正在进行的项目信息
    List<ProjectBean> getCompletedProject(String empID);//查看一个员工已完成的项目信息

    /*查看项目参与信息*/
    List<ProFinishInfoBean> getProjectInfo(String projectID,String empId);
    List<ProFinishInfoBean> getProjectInfoById(String projectID);

    /*查看子任务信息*/
    SubTaskBean getSubTask(String subTaskID);//查看单个子任务信息
    List<SubTaskBean> getProjectSubTask(String projectID);//通过项目查看子任务信息
    List<SubTaskBean> getTaskInfoByEmpIdDoing(String EmpID);//通过员工查看子任务信息
    //查询一个员工已完成的任务信息
    List<SubTaskBean> getTaskInfoByEmpHasDone(String empId);
    List<SubTaskBean> getProjectEmpSubTask(String projectID,String EmpID);//通过员工和项目查看子任务信息

    /*查看子任务参与信息*/
    List<TaskFinishInfoBean> getSubTaskInfo(String subTaskID,String EmpID);
    List<TaskFinishInfoBean> getSubTaskInfoByTaskId(String subTaskID);
    List<TaskFinishInfoBean> getHasFinishTaskFinishInfoByProIDEmpID(String proID,String EmpID);
    List<TaskFinishInfoBean> getTaskFinishInfoByProIDEmpID(String proID,String EmpID);

    /*查看通知信息*/
    NotifyInfoBean getNotifyInfo(int NotifyID);//查看单个通知信息
    List<NotifyInfoBean> getEmpNotifyInfo(String empID);//查看员工通知信息

    /*查询日志*/
    List<LoggerBean> getLog(String projectID);//查询项目日志

    /*查询任务日志*/
    List<TaskLoggerBean> getTaskLog(String taskID);//查询任务日志

}
