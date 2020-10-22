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
    private int empPosition; //员工职位

    public ProFinishInfoBean() {
    }

    public ProFinishInfoBean(String empId, String projectId, int empPosition) {
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

    public int getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(int empPosition) {
        this.empPosition = empPosition;
    }
}
