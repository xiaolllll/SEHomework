package com.example.test.service;

import com.example.test.bean.ProjectBean;
import com.example.test.bean.UserBean;

import java.util.List;

public interface UserService {

    UserBean loginIn(String name,String password);

    List<ProjectBean> getProjectAll();

    void insertProject();
}