package com.example.test;

import com.example.test.bean.ProjectBean;
import com.example.test.bean.UserBean;
import com.example.test.service.UserService;
import com.example.test.serviceImpl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    UserService userService = new UserServiceImpl();

    @Test
    public void contextLoads() {

        userService.insertProject();
        System.out.println("该用户ID为：");
        List<ProjectBean> userBean = userService.getProjectAll();
        System.out.println(userBean.size());
        for (ProjectBean p: userBean) {
            System.out.println(p.getProjectId());
        }
    }

}