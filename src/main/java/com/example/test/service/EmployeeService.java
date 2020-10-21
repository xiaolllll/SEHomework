package com.example.test.service;

import com.example.test.bean.EmployeeBean;

public interface EmployeeService {
    boolean addEmp(EmployeeBean employeeBean);
    boolean deleteEmp(String EmpID);
    boolean modifyEmp(EmployeeBean employeeBean);
}
