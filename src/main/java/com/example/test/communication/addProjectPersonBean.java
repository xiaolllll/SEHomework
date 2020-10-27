package com.example.test.communication;

import java.util.List;

public class addProjectPersonBean {
    String projectID;
    List<String> empIDs;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public List<String> getEmpIDs() {
        return empIDs;
    }

    public void setEmpIDs(List<String> empIDs) {
        this.empIDs = empIDs;
    }
}
