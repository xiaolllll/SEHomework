package com.example.test.service;

import com.example.test.bean.ProjectBean;
import com.example.test.bean.EmployeeBean;

import java.util.ArrayList;

public interface DataQueryService {
    /*员工信息查询*/
    EmployeeBean getEmployee(String empID);//查看单个员工信息
    ArrayList<EmployeeBean> getAllEmployee();//查看所有员工信息
    ArrayList<EmployeeBean> getSkillEmployee(int skillType);//查看具有技能的员工信息
    ArrayList<EmployeeBean> getProjectEmployee(String projectID);//查看一个项目的员工信息
    EmployeeBean getSubTaskEmployee(String subTaskId);//查看一个子任务的员工信息
    EmployeeBean getOutSourceTaskEmployee(String subTaskID);//查看一个子任务的外包员工信息

    /*项目信息查询*/
    ProjectBean getProject(String projectID);//查看单个项目时的信息
    ArrayList<ProjectBean> getAllProject();//查看所有项目信息
    ArrayList<ProjectBean> getEmpProject(String empID);//查看一个员工参加的所有子项目信息
    ArrayList<ProjectBean> getRunProject(String empID);//查看员工正在进行的项目信息
    ArrayList<ProjectBean> getCompletedProject(String empID);//查看一个员工已完成的项目信息

    /*查看项目参与信息*/
    ProFinishInfoBean getProjectInfo(String projectID,String empId);

    /*查看子任务信息*/
    SubTaskBean getSubTask(String subTaskID);//查看单个子任务信息
    ArrayList<SubTaskBean> getProjectSubTask(String projectID);//通过项目查看子任务信息
    ArrayList<SubTaskBean> getEmpSubTask(String EmpID);//通过员工查看子任务信息
    ArrayList<SubTaskBean> getProjectEmpSubTask(String projectID,String EmpID);//通过员工和项目查看子任务信息

    /*查看子任务参与信息*/
    TaskFinishInfo getSubTaskInfo(String subTaskID,String EmpID);
}
