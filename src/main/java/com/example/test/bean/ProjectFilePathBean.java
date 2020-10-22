package com.example.test.bean;

public class ProjectFilePathBean {
    private String projectId;
    private String projectFilePath;

    public ProjectFilePathBean() {
    }

    public ProjectFilePathBean(String projectId, String projectFilePath) {
        this.projectId = projectId;
        this.projectFilePath = projectFilePath;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectFilePath() {
        return projectFilePath;
    }

    public void setProjectFilePath(String projectFilePath) {
        this.projectFilePath = projectFilePath;
    }
}
