package com.example.test;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.ProFinishInfoBean;
import com.example.test.bean.ProjectBean;
import com.example.test.bean.TaskFinishInfoBean;
import com.example.test.service.EmployeeService;
import com.example.test.service.SubTaskService;
import com.example.test.serviceImpl.DataQueryServiceImpl;
import com.example.test.serviceImpl.EmployeeServiceImpl;
import com.example.test.serviceImpl.SubTaskServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSubTaskTests {

    @Autowired
    DataQueryServiceImpl subTaskService = new DataQueryServiceImpl();

    @Test
    public void contextLoads() {
//        //申请外包成功
        ProFinishInfoBean taskFinishInfoBean = subTaskService.getProjectInfo("1", "1");
        System.out.println(taskFinishInfoBean.getEmpId());
        System.out.println(taskFinishInfoBean.getEmpPosition());
//        System.out.println(subTaskService.outSourcingApply(
//                "1", "2", "1-1"));
//        //技能错误
//        System.out.println(subTaskService.outSourcingApply(
//                "1", "3", "1-1"));
//        //无申请者
//        System.out.println(subTaskService.outSourcingApply(
//                "2", "3", "1-1"));
//        //无外包者
//        System.out.println(subTaskService.outSourcingApply(
//                "1", "4", "1-1"));
//        //任务已完成
//        System.out.println(subTaskService.outSourcingApply(
//                "1", "2", "1-2"));
//        //不允许外包
//        System.out.println(subTaskService.outSourcingApply(
//                "1", "2", "1-3"));
        //外包信息交接
//        Date date = new Date();
//        System.out.println(subTaskService.outSourcingHandover(
//                "1", "2", "1-1", date));
//        System.out.println(subTaskService.outSourcingRecovery(
//                "1", "2", "1-1"));
//        System.out.println(subTaskService.subTaskCompleteApply(
//                "1-1"));
//        System.out.println(subTaskService.getEmployee(
//                "1").getEmpName());
//        List<EmployeeBean> list = subTaskService.getAllEmployee();
//        for (EmployeeBean employeeBean : list) {
//            System.out.println(employeeBean.getEmpName());
//        }
//        list = subTaskService.getSkillEmployee(1);
//        for (EmployeeBean employeeBean : list) {
//            System.out.println(employeeBean.getEmpName());
//        }
//        System.out.println();
//        list = subTaskService.getProjectEmployee("1");
//        for (EmployeeBean employeeBean : list) {
//            System.out.println(employeeBean.getEmpName());
//        }
//        System.out.println();
//        EmployeeBean employeeBean = subTaskService.getSubTaskEmployeeDoSelf("1-1");
//        System.out.println(employeeBean.getEmpName());
//        System.out.println();
//        employeeBean = subTaskService.getOutSourceTaskEmployee("1-1");
//        System.out.println(employeeBean.getEmpName());
//        System.out.println(subTaskService.subTaskCompleteRejection(
//                "1-1"));
//        ProjectBean projectBean = subTaskService.getProject("1");
//        System.out.println(projectBean.getProjectDesc());
//        List<ProjectBean> list = subTaskService.getAllProject();
//        for (ProjectBean projectBean1 : list) {
//            System.out.println(projectBean1.getProjectDesc());
//        }
//        System.out.println();
//        list = subTaskService.getEmpProject("1");
//        for (ProjectBean projectBean1 : list) {
//            System.out.println(projectBean1.getProjectDesc());
//        }
//        System.out.println();
//        list = subTaskService.getRunProject("1");
//        for (ProjectBean projectBean1 : list) {
//            System.out.println(projectBean1.getProjectDesc());
//        }
//        System.out.println();
//        list = subTaskService.getCompletedProject("2");
//        for (ProjectBean projectBean1 : list) {
//            System.out.println(projectBean1.getProjectDesc());
//        }
//    }
    }
}