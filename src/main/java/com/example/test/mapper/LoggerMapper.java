package com.example.test.mapper;

import com.example.test.bean.LoggerBean;
import com.example.test.bean.TaskLoggerBean;

import java.util.List;

public interface LoggerMapper {
    /**
     * to do
     */
    //根据任务查询日志
    public List<String> getLogger(String projectId);

    /**
     * to do
     */
    //添加日志
    public int addLogger(LoggerBean logger);

}
