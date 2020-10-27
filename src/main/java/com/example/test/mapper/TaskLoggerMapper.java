package com.example.test.mapper;

import com.example.test.bean.TaskLoggerBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TaskLoggerMapper {
    //根据项目查询日志
     List<TaskLoggerBean> getLogger(String taskId);

    //添加日志
     int addLogger(TaskLoggerBean logger);

    //获取所有日志
     List<String> getAllLogger();
}
