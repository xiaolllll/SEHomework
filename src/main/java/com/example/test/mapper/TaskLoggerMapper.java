package com.example.test.mapper;

import com.example.test.bean.TaskLoggerBean;

import java.util.List;

public interface TaskLoggerMapper {
    //根据项目查询日志
    public List<String> getLogger(String taskId);

    //添加日志
    public int addLogger(TaskLoggerBean logger);

    //获取所有日志
    public List<String> getAllLogger();
}
