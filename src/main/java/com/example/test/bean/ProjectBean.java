package com.example.test.bean;

import java.sql.Timestamp;
import java.util.Date;

public class ProjectBean {
    private String projectId;
    private String projectName;
    private String proManagerId;
    private String projectDesc;
    private int projectState;
    private Date projectStartTime;
    private Date projectEndTime;

    public ProjectBean(String projectId, String projectName, String proManagerId, String projectDesc,
                       int projectState, Date projectStartTime, Date projectEndTime) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.proManagerId = proManagerId;
        this.projectDesc = projectDesc;
        this.projectState = projectState;
        this.projectStartTime = projectStartTime;
        this.projectEndTime = projectEndTime;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProManagerId() {
        return proManagerId;
    }

    public void setProManagerId(String proManagerId) {
        this.proManagerId = proManagerId;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public int getProjectState() {
        return projectState;
    }

    public void setProjectState(int projectState) {
        this.projectState = projectState;
    }

    public Date getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(Date projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public Date getProjectEndTime() {
        return projectEndTime;
    }

    public void setProjectEndTime(Date projectEndTime) {
        this.projectEndTime = projectEndTime;
    }
}
