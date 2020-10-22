package com.example.test;

import com.example.test.service.EmployeeService;
import com.example.test.service.SubTaskService;
import com.example.test.serviceImpl.EmployeeServiceImpl;
import com.example.test.serviceImpl.SubTaskServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSubTaskTests {

    @Autowired
    SubTaskService subTaskService = new SubTaskServiceImp();

    @Test
    public void contextLoads() {
        //申请外包成功
        System.out.println(subTaskService.outSourcingApply(
                "1", "2", "1-1"));
        //技能错误
        System.out.println(subTaskService.outSourcingApply(
                "1", "3", "1-1"));
        //无申请者
        System.out.println(subTaskService.outSourcingApply(
                "2", "3", "1-1"));
        //无外包者
        System.out.println(subTaskService.outSourcingApply(
                "1", "4", "1-1"));
        //任务已完成
        System.out.println(subTaskService.outSourcingApply(
                "1", "2", "1-2"));
        //不允许外包
        System.out.println(subTaskService.outSourcingApply(
                "1", "2", "1-3"));
        //外包信息交接
        Date date = new Date();
        //todo project id is null
        System.out.println(subTaskService.outSourcingHandover(
                "1", "2", "1-1", date));
    }

}