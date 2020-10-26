package com.example.test.mapper;

import com.example.test.bean.EmployeeBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface EmployeeMapper {
    //根据员工ID查询信息
    EmployeeBean getEmpInfoByEmpId(String empId);
    //根据员工名查询项目信息
    EmployeeBean getEmpInfoByEmpName(String empName);
    //查询所有员工信息
    List<EmployeeBean> getEmpInfoAll();
    //根据项目Id查询员工信息
    List<EmployeeBean> getEmpInfoByProId(String proId);
    //根据任务Id查询自己做的员工信息
    EmployeeBean getEmpInfoByTaskIdDoSelf(String taskId);
    //根据任务Id查询外包的员工信息
    EmployeeBean getEmpInfoByTaskIdOutSource(String taskId);
    //根据任务Id查询员工信息
    List<EmployeeBean> getEmpInfoByTaskId(String taskId);
    //插入数据
    int insertEmployee(EmployeeBean employeeBean);
    //更新数据
    int updateEmployee(EmployeeBean employeeBean);
    //删除数据
    int deleteEmployee(String empId);
    List<EmployeeBean> getSkillEmployee(int skillType);//查看具有技能的员工信息
    EmployeeBean getOutSourceTaskEmployee(String subTaskID);//查看一个子任务的外包员工信息
}
