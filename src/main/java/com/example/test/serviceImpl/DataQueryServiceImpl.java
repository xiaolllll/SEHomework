package com.example.test.serviceImpl;

import com.example.test.bean.*;
import com.example.test.mapper.*;
import com.example.test.service.DataQueryService;
import com.example.test.util.ServiceUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataQueryServiceImpl implements DataQueryService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProFinishInfoMapper proFinishInfoMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private TaskFinishInfoMapper taskFinishInfoMapper;
    @Autowired
    private NotifyInfoMapper notifyInfoMapper;
    @Autowired
    private LoggerMapper loggerMapper;
    @Autowired
    private TaskLoggerMapper taskLoggerMapper;

    /*员工信息查询*/
    public EmployeeBean getEmployee(String empID) {
        return employeeMapper.getEmpInfoByEmpId(empID);
    }//查看单个员工信息

    public List<EmployeeBean> getAllEmployee() {
        return employeeMapper.getEmpInfoAll();
    }//查看所有员工信息

    @Override
    public List<EmployeeBean> getSkillEmployee(int skillType) {
        List<EmployeeBean> list = employeeMapper.getEmpInfoAll();
        List<EmployeeBean> res = new ArrayList<>();
        for (EmployeeBean employeeBean : list) {
            if (employeeBean.hasSkill(skillType)) {
                res.add(employeeBean);
            }
        }
        return res;
    }

    @Override
    public List<EmployeeBean> getProjectEmployee(String projectID) {
        return employeeMapper.getEmpInfoByProId(projectID);
    }

    @Override
    public EmployeeBean getSubTaskEmployeeDoSelf(String subTaskId) {
        return employeeMapper.getEmpInfoByTaskIdDoSelf(subTaskId);
    }

    @Override
    public EmployeeBean getOutSourceTaskEmployee(String subTaskID) {
        return employeeMapper.getEmpInfoByTaskIdOutSource(subTaskID);
    }

    @Override
    public List<EmployeeBean> getManagerInfo() {
        return employeeMapper.getManagerInfo();
    }

    @Override
    public ProjectBean getProject(String projectID) {
        return projectMapper.getProInfoByProId(projectID);
    }

    @Override
    public List<ProjectBean> getAllProject() {
        return projectMapper.getProInfoAll();
    }

    @Override
    public List<ProjectBean> getEmpProject(String empID) {
        return projectMapper.getEmpProject(empID);
    }

    @Override
    public List<ProjectBean> getRunProject(String empID) {
        return projectMapper.getProInfoByEmpIdDoing(empID);
    }

    @Override
    public List<ProjectBean> getCompletedProject(String empID) {
        return projectMapper.getProInfoByEmpHasDone(empID);
    }

    @Override
    public List<ProFinishInfoBean> getProjectInfo(String projectID, String empId) {
        return proFinishInfoMapper.getProjectInfo(projectID, empId);
    }

    @Override
    public SubTaskBean getSubTask(String subTaskID) {
        return subTaskMapper.getTaskInfoByProId(subTaskID);
    }

    @Override
    public List<SubTaskBean> getProjectSubTask(String projectID) {
        return subTaskMapper.getProjectSubTask(projectID);
    }

    @Override
    public List<SubTaskBean> getTaskInfoByEmpIdDoing(String EmpID) {
        return subTaskMapper.getTaskInfoByEmpIdDoing(EmpID);
    }

    @Override
    public List<SubTaskBean> getTaskInfoByEmpHasDone(String empId) {
        return subTaskMapper.getTaskInfoByEmpHasDone(empId);
    }

    @Override
    public List<SubTaskBean> getProjectEmpSubTask(String projectID, String EmpID) {
        return subTaskMapper.getProjectEmpSubTask(projectID, EmpID);
    }

    @Override
    public List<TaskFinishInfoBean> getSubTaskInfo(String subTaskID, String EmpID) {
        return taskFinishInfoMapper.getSubTaskInfo(subTaskID, EmpID);
    }

    @Override
    public NotifyInfoBean getNotifyInfo(int NotifyID) {
        return notifyInfoMapper.selectNotifyInfoByNotifyId(NotifyID);
    }

    //查询发送者信息
    @Override
    public List<NotifyInfoBean> getEmpNotifyInfo(String empID) {
        return notifyInfoMapper.getNotifyInfoBySenderID(empID);
    }

    @Override
    public List<LoggerBean> getLog(String projectID) {
        return loggerMapper.getLogger(projectID);
    }

    @Override
    public List<TaskLoggerBean> getTaskLog(String taskID) {
        return taskLoggerMapper.getLogger(taskID);
    }


}
