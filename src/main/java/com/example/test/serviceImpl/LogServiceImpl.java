package com.example.test.serviceImpl;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.LoggerBean;
import com.example.test.mapper.EmployeeMapper;
import com.example.test.mapper.LoggerMapper;
import com.example.test.service.LogService;
import com.example.test.service.LoginService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class LogServiceImpl implements LogService {
    @Autowired
    private EmployeeMapper employeeMapper;
    private LoggerMapper loggerMapper;
    @Override
    public String addLog(String projectID, String empID, String content) {
        LoggerBean loggerBean = new LoggerBean();
        loggerBean.setProjectId(projectID);
        EmployeeBean employeeBean = employeeMapper.getEmpInfoByEmpId(empID);
        String empName="";
        if(employeeBean!=null){
            empName = employeeBean.getEmpName();
        }
        loggerBean.setContent("操作者："+empName+"("+empID+")"+"\t操作："+content);
        /**
         * 没写完
         */
    }
}
