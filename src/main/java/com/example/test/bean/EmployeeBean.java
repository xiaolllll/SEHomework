package com.example.test.bean;

/**
 *   `empId` varchar(32) NOT NULL,
 *   `empName` varchar(32) NOT NULL,
 *   `empPassword` varchar(32) NOT NULL,
 *   `empFinishProCount` int(32) DEFAULT 0,
 *   `empDoingProCount` int(32) DEFAULT 0,
 *   `empSkilltype` int(32) NOT NULL,
 *   `empType` int(32) NOT NULL, # 员工为1， 管理员为0
 */
public class EmployeeBean {
    private String empId;
    private String empName;
    private String empPassword;
    private int empFinishProCount;
    private int empDoingProCount;
    private int empSkillType;
    private int empType; // 员工为1，管理员为0

    public EmployeeBean() {
    }

    public EmployeeBean(String empId, String empName, String empPassword, int empFinishProCount, int empDoingProCount, int empSkillType, int empType) {
        this.empId = empId;
        this.empName = empName;
        this.empPassword = empPassword;
        this.empFinishProCount = empFinishProCount;
        this.empDoingProCount = empDoingProCount;
        this.empSkillType = empSkillType;
        this.empType = empType;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public int getEmpFinishProCount() {
        return empFinishProCount;
    }

    public void setEmpFinishProCount(int empFinishProCount) {
        this.empFinishProCount = empFinishProCount;
    }

    public int getEmpDoingProCount() {
        return empDoingProCount;
    }

    public void setEmpDoingProCount(int empDoingProCount) {
        this.empDoingProCount = empDoingProCount;
    }

    public int getEmpSkillType() {
        return empSkillType;
    }

    public void setEmpSkillType(int empSkillType) {
        this.empSkillType = empSkillType;
    }

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }
}
