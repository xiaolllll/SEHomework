package com.example.test.serviceImpl;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.LoggerBean;
import com.example.test.mapper.EmployeeMapper;
import com.example.test.mapper.LoggerMapper;
import com.example.test.service.LogService;
import com.example.test.service.LoginService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private LoggerMapper loggerMapper;
    @Override
    public String addLog(String projectID, String empID, String content) {
        LoggerBean loggerBean = new LoggerBean();
        loggerBean.setProjectId(projectID);

        EmployeeBean employeeBean = employeeMapper.getEmpInfoByEmpId(empID);
        String empName="";
        if(employeeBean!=null){
            System.out.println("LogServiceImpl addLog 找不到操作人");
            empName = employeeBean.getEmpName();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=simpleDateFormat.format(System.currentTimeMillis());

        loggerBean.setContent("时间："+time+"\t操作者："+empName+"("+empID+")"+"\t操作："+content);
        int result=loggerMapper.addLogger(loggerBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库插入项目日志失败";
        }

        return ServiceUtil.SUCCESS;
    }
}
