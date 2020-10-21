package com.example.test.service;

import com.example.test.bean.EmployeeBean;

public interface EmployeeService {
    String addEmp(EmployeeBean employeeBean);
    String deleteEmp(String empID);
    String modifyEmp(EmployeeBean employeeBean);
}
