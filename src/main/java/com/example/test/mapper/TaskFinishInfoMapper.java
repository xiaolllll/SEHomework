package com.example.test.mapper;

import com.example.test.bean.TaskFinishInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskFinishInfoMapper {
    List<TaskFinishInfoBean> getSubTaskInfo(@Param("subTaskID") String subTaskID, @Param("EmpID") String EmpID);

    List<TaskFinishInfoBean> getSubTaskInfoByTaskId(@Param("subTaskID") String subTaskID);


    TaskFinishInfoBean getSubTaskInfoByTaskIdEmpIdDoType(@Param("subTaskID") String subTaskID, @Param("EmpID") String EmpID,
                                                         @Param("doType") int doType);

    int insertTaskFinishInfo(TaskFinishInfoBean taskFinishInfoBean);

    int updateTaskFinishInfo(TaskFinishInfoBean taskFinishInfoBean);

    List<TaskFinishInfoBean> getHasFinishHasNotOutSourceTaskFinishInfoByProID(@Param("proID") String proID);

    List<TaskFinishInfoBean> getHasFinishTaskFinishInfoByProIDEmpID(@Param("proID") String proID, @Param("EmpID") String EmpID);

    List<TaskFinishInfoBean> getTaskFinishInfoByProIDEmpID(@Param("proID") String proID, @Param("EmpID") String EmpID);

    int deleteTaskFinishInfo(@Param("subTaskID") String subTaskID, @Param("EmpID") String EmpID, @Param("doType") int doType);

    int deleteTaskAllInfo(String subTaskId);
}
