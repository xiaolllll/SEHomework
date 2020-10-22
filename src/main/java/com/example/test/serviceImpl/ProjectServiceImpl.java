package com.example.test.serviceImpl;

import com.example.test.bean.ProjectBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.service.ProjectService;

import java.util.ArrayList;

public class ProjectServiceImpl implements ProjectService {
    @Override
    public String addSubTask(SubTaskBean taskBean, ArrayList<String> leadingPath, ArrayList<String> succeedingPath, boolean isChain) {
        return null;
    }

    @Override
    public String deleteSubTask(String SubTaskID, boolean isChain) {
        return null;
    }

    @Override
    public String modifySubTask(SubTaskBean taskBean) {
        return null;
    }

    @Override
    public String restartSubTask(String SubTaskID, boolean isChain) {
        return null;
    }

    @Override
    public String forceCompleteSubTask(String SubTaskID) {
        return null;
    }

    @Override
    public String setSubTaskPerson(String SubTaskID, String empID) {
        return null;
    }

    @Override
    public String projectCompleteApply(String projectID) {
        return null;
    }

    @Override
    public String createProject(ProjectBean projectBean) {
        return null;
    }

    @Override
    public String storageProject(String projectID) {
        return null;
    }

    @Override
    public String restartProject(String projectID) {
        return null;
    }

    @Override
    public String abandonProject(String projectID) {
        return null;
    }

    @Override
    public String completeProject(String projectID) {
        return null;
    }

    @Override
    public String modifyProject(String projectID) {
        return null;
    }

    @Override
    public String setProjectManager(String projectID, String empID) {
        return null;
    }

    @Override
    public String addProjectPerson(String projectID, String empID) {
        return null;
    }

    @Override
    public String deleteProjectPerson(String projectID, String empID) {
        return null;
    }
}
