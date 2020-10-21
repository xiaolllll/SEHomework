package com.example.test.bean;

public class TaskLoggerBean {
    private String taskLoggerID;
    private String subTaskId;
    private String content;

    public TaskLoggerBean(String subTaskId, String content) {
        this.subTaskId = subTaskId;
        this.content = content;
    }

    public String getTaskLoggerID() {
        return taskLoggerID;
    }

    public void setTaskLoggerID(String taskLoggerID) {
        this.taskLoggerID = taskLoggerID;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
