package com.example.test.bean;

public class TaskFileListBean {
    private String subTaskId;
    private String filePath;

    public TaskFileListBean() {
    }

    public TaskFileListBean(String subTaskId, String filePath) {
        this.subTaskId = subTaskId;
        this.filePath = filePath;
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
}
