package com.example.test.bean;

import java.util.Date;

/**
 *   `subtaskId` varchar(32) NOT NULL,
 *   `subtaskName` varchar(32) NOT NULL,
 *   `subtaskDesc` varchar(255),
 *   `subtaskCanOutSource` int(32) NOT NULL,
 *   `subtaskSkillType` int(32) NOT NULL,
 *   `subtaskInProject` varchar(32) NOT NULL,
 *   `subtaskState` int(32) NOT NULL,
 *   `subtaskStartTime` DateTime,
 *   `subTaskEndTime` DateTime,
 *   PRIMARY KEY (`subtaskId`)
 */

public class SubTaskBean {

    private String subTaskId;
    private String subTaskName;
    private String subTaskDesc;
    private int SubTaskCanOutSource;
    private int subTaskSkillType;
    private String subTaskInProjectId;
    private int subTaskState;
    private Date subTaskStartTime;
    private Date subTaskEndTime;

    public SubTaskBean(String subTaskId, String subTaskName, String subTaskDesc, int subTaskCanOutSource, int subTaskSkillType, String subTaskInProjectId, int subTaskState, Date subTaskStartTime, Date subTaskEndTime) {
        this.subTaskId = subTaskId;
        this.subTaskName = subTaskName;
        this.subTaskDesc = subTaskDesc;
        SubTaskCanOutSource = subTaskCanOutSource;
        this.subTaskSkillType = subTaskSkillType;
        this.subTaskInProjectId = subTaskInProjectId;
        this.subTaskState = subTaskState;
        this.subTaskStartTime = subTaskStartTime;
        this.subTaskEndTime = subTaskEndTime;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public String getSubTaskDesc() {
        return subTaskDesc;
    }

    public void setSubTaskDesc(String subTaskDesc) {
        this.subTaskDesc = subTaskDesc;
    }

    public int getSubTaskCanOutSource() {
        return SubTaskCanOutSource;
    }

    public void setSubTaskCanOutSource(int subTaskCanOutSource) {
        SubTaskCanOutSource = subTaskCanOutSource;
    }

    public int getSubTaskSkillType() {
        return subTaskSkillType;
    }

    public void setSubTaskSkillType(int subTaskSkillType) {
        this.subTaskSkillType = subTaskSkillType;
    }

    public String getSubTaskInProjectId() {
        return subTaskInProjectId;
    }

    public void setSubTaskInProjectId(String subTaskInProjectId) {
        this.subTaskInProjectId = subTaskInProjectId;
    }

    public int getSubTaskState() {
        return subTaskState;
    }

    public void setSubTaskState(int subTaskState) {
        this.subTaskState = subTaskState;
    }

    public Date getSubTaskStartTime() {
        return subTaskStartTime;
    }

    public void setSubTaskStartTime(Date subTaskStartTime) {
        this.subTaskStartTime = subTaskStartTime;
    }

    public Date getSubTaskEndTime() {
        return subTaskEndTime;
    }

    public void setSubTaskEndTime(Date subTaskEndTime) {
        this.subTaskEndTime = subTaskEndTime;
    }
}
