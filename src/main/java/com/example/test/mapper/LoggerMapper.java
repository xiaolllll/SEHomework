package com.example.test.mapper;

import com.example.test.bean.LoggerBean;

import java.util.List;

public interface LoggerMapper {
    //根据项目查询日志
    public List<String> getLogger(String proId);

    //添加日志
    public int addLogger(LoggerBean logger);

    //获取所有日志
    public List<String> getAllLogger();
}
