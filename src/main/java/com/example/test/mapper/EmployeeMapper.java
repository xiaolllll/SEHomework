package com.example.test.mapper;

import com.example.test.bean.EmployeeBean;

import java.util.List;

public interface EmployeeMapper {
    //根据员工ID查询信息
    public EmployeeBean getEmpInfoByEmpId(String empId);
    //根据员工名查询项目信息
    public EmployeeBean getEmpInfoByEmpName(String empName);
    //查询所有员工信息
    public List<EmployeeBean> getEmpInfoAll();
    //根据项目Id查询员工信息
    public List<EmployeeBean> getEmpInfoByProId(String proId);
    //根据任务Id查询员工信息
    public List<EmployeeBean> getEmpInfoByTaskId(String taskId);
    //插入数据
    public int insertEmployee(EmployeeBean employeeBean);
    //更新数据
    public int updateEmployee(EmployeeBean employeeBean);
    //删除数据
    public int deleteEmployee(String empId);
    public List<EmployeeBean> getSkillEmployee(int skillType);//查看具有技能的员工信息
    public EmployeeBean getOutSourceTaskEmployee(String subTaskID);//查看一个子任务的外包员工信息
}
