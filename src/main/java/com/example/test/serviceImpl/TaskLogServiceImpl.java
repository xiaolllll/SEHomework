package com.example.test.serviceImpl;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.LoggerBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.bean.TaskLoggerBean;
import com.example.test.mapper.EmployeeMapper;
import com.example.test.mapper.TaskLoggerMapper;
import com.example.test.service.TaskLogService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
@Service
public class TaskLogServiceImpl implements TaskLogService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskLoggerMapper taskLoggerMapper;
    @Override
    public String addTaskLog(String subTaskID, String empID, String content) {
        TaskLoggerBean taskLoggerBean = new TaskLoggerBean();

        taskLoggerBean.setSubTaskId(subTaskID);

        EmployeeBean employeeBean = employeeMapper.getEmpInfoByEmpId(empID);
        String empName="";
        if(employeeBean!=null){
            System.out.println("TaskLogServiceImpl addTaskLog 找不到操作人");
            empName = employeeBean.getEmpName();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=simpleDateFormat.format(System.currentTimeMillis());

        taskLoggerBean.setContent("时间："+time+"\t操作者："+empName+"("+empID+")"+"\t操作："+content);
        int result=taskLoggerMapper.addLogger(taskLoggerBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库插入项目日志失败";
        }

        return ServiceUtil.SUCCESS;
    }
}
