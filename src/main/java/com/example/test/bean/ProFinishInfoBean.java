package com.example.test.bean;

/**
 * CREATE TABLE `proFinishInfo` (
 * `empId` varchar(32) NOT NULL,
 * `projectId` varchar(32) NOT NULL,
 * `empPostion` int(32) NOT NULL
 */
public class ProFinishInfoBean {
    private String empId;
    private String projectId;
    private String empPosition; //员工职位

    public ProFinishInfoBean(String empId, String projectId, String empPosition) {
        this.empId = empId;
        this.projectId = projectId;
        this.empPosition = empPosition;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }
}
