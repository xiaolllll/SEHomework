package com.example.test.bean;

import java.util.Date;

public class TaskFinishInfoBean {
    private String empId;
    private String subTaskId;
    private String projectId;
    private int doType; //0为外包，1为非外包
    private Date subTaskOutSourceEndTime;

    public TaskFinishInfoBean() {
    }

    public TaskFinishInfoBean(String empId, String subTaskId,
                              String projectId, int doType, Date subTaskOutSourceEndTime) {
        this.empId = empId;
        this.subTaskId = subTaskId;
        this.projectId = projectId;
        this.doType = doType;
        this.subTaskOutSourceEndTime = subTaskOutSourceEndTime;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getDoType() {
        return doType;
    }

    public void setDoType(int doType) {
        this.doType = doType;
    }

    public Date getSubTaskOutSourceEndTime() {
        return subTaskOutSourceEndTime;
    }

    public void setSubTaskOutSourceEndTime(Date subTaskOutSourceEndTime) {
        this.subTaskOutSourceEndTime = subTaskOutSourceEndTime;
    }
}
