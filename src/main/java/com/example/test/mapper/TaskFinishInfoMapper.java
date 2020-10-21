package com.example.test.mapper;

import com.example.test.bean.TaskFinishInfoBean;
import org.apache.ibatis.annotations.Param;

public interface TaskFinishInfoMapper {
    public TaskFinishInfoBean getSubTaskInfo(@Param("subTaskID") String subTaskID, @Param("EmpID") String EmpID);
}
