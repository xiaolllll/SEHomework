package com.example.test.bean;

import java.util.Date;

public class TaskFileListBean {
    private String empId;
    private String subTaskId;
    private String filePath;
    private Date commitDate;

    public TaskFileListBean() {
    }

    public TaskFileListBean(String subTaskId, String filePath) {
        this.subTaskId = subTaskId;
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }
}
