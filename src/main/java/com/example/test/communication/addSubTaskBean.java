package com.example.test.communication;

import com.example.test.bean.SubTaskBean;

import java.util.ArrayList;

public class addSubTaskBean {
    SubTaskBean taskBean;
    ArrayList<String> leadingPath;
    java.util.ArrayList<String> succeedingPath;
    boolean isChain;

    public SubTaskBean getTaskBean() {
        return taskBean;
    }

    public void setTaskBean(SubTaskBean taskBean) {
        this.taskBean = taskBean;
    }

    public ArrayList<String> getLeadingPath() {
        return leadingPath;
    }

    public void setLeadingPath(ArrayList<String> leadingPath) {
        this.leadingPath = leadingPath;
    }

    public ArrayList<String> getSucceedingPath() {
        return succeedingPath;
    }

    public void setSucceedingPath(ArrayList<String> succeedingPath) {
        this.succeedingPath = succeedingPath;
    }

    public boolean isChain() {
        return isChain;
    }

    public void setChain(boolean chain) {
        isChain = chain;
    }
}
