package com.example.test.mapper;

import com.example.test.bean.SubTaskBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubTaskMapper {
    //根据任务ID查询项目信息
    SubTaskBean getTaskInfoByProId(String subTaskId);

    //根据任务名查询项目信息
    SubTaskBean getTaskInfoByProName(String subTaskName);

    //查看所有项目信息
    List<SubTaskBean> getTaskInfoAll();

    List<SubTaskBean> getProjectSubTask(String projectID);//通过项目查看子任务信息

    List<SubTaskBean> getProjectEmpSubTask(@Param("projectID") String projectID, @Param("EmpID") String EmpID);//通过员工和项目查看子任务信息

    //查询一个员工正在进行的任务
    List<SubTaskBean> getTaskInfoByEmpIdDoing(String empId);

    //查询一个员工已完成的任务信息
    List<SubTaskBean> getTaskInfoByEmpHasDone(String empId);

    //寻找下一个任务
    List<SubTaskBean> getNextTaskListByTaskId(String taskId);

    //插入数据
    int insertSubTask(SubTaskBean subTaskBean);

    //更新数据
    int updateSubTask(SubTaskBean subTaskBean);

    //删除数据
    int deleteSubTask(String subTaskId);
}
