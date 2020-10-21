package com.example.test.mapper;

import com.example.test.bean.SubTaskBean;

import java.util.List;

public interface SubTaskMapper {
    //根据任务ID查询项目信息
    public SubTaskBean getTaskInfoByProId(String subTaskId);
    //根据任务名查询项目信息
    public SubTaskBean getTaskInfoByProName(String subTaskName);
    //查看所有项目信息
    public List<SubTaskBean> getTaskInfoAll();
    //查询一个员工正在进行的任务
    public List<SubTaskBean> getTaskInfoByEmpIdDoing(String empId);
    //查询一个员工已完成的任务信息
    public List<SubTaskBean> getTaskInfoByEmpHasDone(String empId);
    //寻找下一个任务
    public List<SubTaskBean> getNextTaskListByTaskId(String taskId);
    //插入数据
    public int insertSubTask(SubTaskBean subTaskBean);
    //更新数据
    public int updateSubTask(SubTaskBean subTaskBean);
    //删除数据
    public int deleteSubTask(String subTaskId);
}
