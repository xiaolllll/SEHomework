package com.example.test.service;

import com.example.test.bean.EmployeeBean;

public interface LoginService {
    EmployeeBean loginIn(String ID, String password);
    boolean changePassword(String ID,String oldPassword,String newPassword);
}
