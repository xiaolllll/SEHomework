package com.example.test.bean;

import java.util.Date;

public class TaskFinishInfoBean {
    private String empId;
    private String subTaskId;
    private String projectId;
    private int doType;
    private int totalFileCount;
    private int hasFinishFileCount;
    private Date subTaskOutSourceEndTime;

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

    public int getTotalFileCount() {
        return totalFileCount;
    }

    public void setTotalFileCount(int totalFileCount) {
        this.totalFileCount = totalFileCount;
    }

    public int getHasFinishFileCount() {
        return hasFinishFileCount;
    }

    public void setHasFinishFileCount(int hasFinishFileCount) {
        this.hasFinishFileCount = hasFinishFileCount;
    }
}
