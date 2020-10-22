package com.example.test.serviceImpl;

import com.example.test.bean.EmployeeBean;
import com.example.test.mapper.EmployeeMapper;
import com.example.test.service.LoginService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public EmployeeBean loginIn(String empID, String password) {
        EmployeeBean employeeBean=employeeMapper.getEmpInfoByEmpId(empID);
        if(employeeBean!=null&&employeeBean.getEmpPassword().equals(password))
            return employeeBean;
        else
            return null;
    }

    @Override
    public String changePassword(String empID, String oldPassword, String newPassword) {
        EmployeeBean employeeBean=employeeMapper.getEmpInfoByEmpId(empID);
        if(employeeBean==null){
            return ServiceUtil.FAILURE+"员工不存在";
        }
        else if(!employeeBean.getEmpPassword().equals(oldPassword)){
            return ServiceUtil.FAILURE+"密码错误";
        }
        else{
            employeeBean.setEmpPassword(newPassword);
            int result = employeeMapper.updateEmployee(employeeBean);
            if(result!=1){
                return ServiceUtil.FAILURE+"更新数据库失败";
            }
            else
                return ServiceUtil.SUCCESS;
        }
    }
}
