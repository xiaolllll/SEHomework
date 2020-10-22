package com.example.test.bean;

public class TaskNextBean {
    private String subTaskId;
    private String nextTaskId;

    public TaskNextBean() {
    }

    public TaskNextBean(String subTaskId, String nextTaskId) {
        this.subTaskId = subTaskId;
        this.nextTaskId = nextTaskId;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getNextTaskId() {
        return nextTaskId;
    }

    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
    }
}
