package com.example.test.service;

import com.example.test.bean.UserBean;

public interface EmployeeService {
    boolean addEmp(EmployeeBean employeeBean);
    boolean deleteEmp(String EmpID);
    boolean modifyEmp(EmployeeBean employeeBean);
}
