package com.example.test.service;

import com.example.test.bean.EmployeeBean;

public interface LoginService {
    EmployeeBean loginIn(String empID, String password);
    String changePassword(String empID,String oldPassword,String newPassword);
}
