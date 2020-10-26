package com.example.test.mapper;

import com.example.test.bean.TaskNextBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TaskNextMapper {
    //插入
    int insertTaskNext(TaskNextBean taskNextBean);

}
