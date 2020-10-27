package com.example.test.mapper;

import com.example.test.bean.TaskNextBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskNextMapper {
    //插入
    int insertTaskNext(TaskNextBean taskNextBean);

    int deleteTaskId(String subTaskId);

    int deleteTaskNextId(String subTaskNextId);

    List<TaskNextBean> getBeforeTaskId(String subTaskId);
}
