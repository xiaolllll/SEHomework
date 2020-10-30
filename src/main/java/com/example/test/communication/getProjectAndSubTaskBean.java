package com.example.test.communication;

import com.example.test.bean.ProjectBean;
import com.example.test.bean.SubTaskBean;

import java.util.List;

public class getProjectAndSubTaskBean {
    ProjectBean projectBean;
    List<SubTaskBean> list;

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
    }

    public List<SubTaskBean> getList() {
        return list;
    }

    public void setList(List<SubTaskBean> list) {
        this.list = list;
    }
}
