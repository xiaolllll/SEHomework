package com.example.test.mapper;

import com.example.test.bean.LoggerBean;
import com.example.test.bean.TaskLoggerBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface LoggerMapper {
    /**
     * to do
     */
    //根据任务查询日志
     List<String> getLogger(String projectId);

    /**
     * to do
     */
    //添加日志
     int addLogger(LoggerBean logger);

}
