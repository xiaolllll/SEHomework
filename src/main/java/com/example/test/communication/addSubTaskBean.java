package com.example.test.communication;

import com.example.test.bean.SubTaskBean;

import java.util.ArrayList;
import java.util.List;

public class addSubTaskBean {
    SubTaskBean taskBean;
    List<String> leadingPath;
    List<String> succeedingPath;
    boolean isChain;

    public SubTaskBean getTaskBean() {
        return taskBean;
    }

    public void setTaskBean(SubTaskBean taskBean) {
        this.taskBean = taskBean;
    }

    public List<String> getLeadingPath() {
        return leadingPath;
    }

    public void setLeadingPath(ArrayList<String> leadingPath) {
        this.leadingPath = leadingPath;
    }

    public List<String> getSucceedingPath() {
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
