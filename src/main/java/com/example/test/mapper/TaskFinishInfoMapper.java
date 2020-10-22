package com.example.test.mapper;

import com.example.test.bean.TaskFinishInfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskFinishInfoMapper {
    public List<TaskFinishInfoBean> getSubTaskInfo(@Param("subTaskID") String subTaskID, @Param("EmpID") String EmpID);

    public int insertTaskFinishInfo(TaskFinishInfoBean taskFinishInfoBean);

    public int updateTaskFinishInfo(TaskFinishInfoBean taskFinishInfoBean);

    public int deleteTaskFinishInfo(@Param("subTaskID") String subTaskID, @Param("EmpID") String EmpID, @Param("doType") String doType);
}
