package com.example.test.service;

import com.example.test.bean.*;

import java.util.ArrayList;

public interface ProjectService {
    /*子任务管理*/
    String addSubTask(SubTaskBean taskBean, ArrayList<String> leadingPath,ArrayList<String> succeedingPath,boolean isChain);//增加子任务
    String deleteSubTask(String SubTaskID);//删除子任务
    String modifySubTask(SubTaskBean taskBean);//修改子任务信息
    String restartSubTask(String SubTaskID,boolean isChain);//重启子任务,将任务置为未完成状态，同时可以选择是否重启关联的之后任务
    String forceCompleteSubTask(String SubTaskID);//强制完成子任务
    String setSubTaskPerson(String SubTaskID,String empID);//设置完成子任务的人员

    /*项目管理*/
    String projectCompleteApply(String projectID);//申请结项
    String createProject(ProjectBean projectBean);//创建项目
    String storageProject(String projectID);//暂存项目
    String restartProject(String projectID);//启动项目
    String abandonProject(String projectID);//废弃项目
    String completeProject(String projectID);//完成项目
    String modifyProject(String projectID);//修改项目信息
    String setProjectManager(String projectID,String empID);//项目管理员设置
    String addProjectPerson(String projectID,String empID);//增加项目成员
    String deleteProjectPerson(String projectID,String empID);//删除项目成员
}
