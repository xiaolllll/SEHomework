package com.example.test.serviceImpl;

import com.example.test.bean.EmployeeBean;
import com.example.test.mapper.EmployeeMapper;
import com.example.test.service.EmployeeService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public String addEmp(EmployeeBean employeeBean) {
        int result = employeeMapper.insertEmployee(employeeBean);
        if(result==1){
            return ServiceUtil.SUCESS;
        }
        else {
            return ServiceUtil.FAILURE+"数据库插入失败";
        }
    }

    @Override
    public String deleteEmp(String empID) {
        int result = employeeMapper.deleteEmployee(empID);
        if(result==1){
            return ServiceUtil.SUCESS;
        }
        else {
            return ServiceUtil.FAILURE+"数据库插入失败";
        }
    }

    @Override
    public String modifyEmp(EmployeeBean employeeBean) {
        int result = employeeMapper.updateEmployee(employeeBean);
        if(result==1){
            return ServiceUtil.SUCESS;
        }
        else {
            return ServiceUtil.FAILURE+"数据库插入失败";
        }
    }
}
