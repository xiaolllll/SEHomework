package com.example.test.bean;

/**
 *   `projectId` varchar(32) NOT NULL,
 *   `content` blob
 */
public class LoggerBean {
    private String projectId;
    private String content;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
