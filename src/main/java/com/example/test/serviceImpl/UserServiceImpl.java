package com.example.test.serviceImpl;

import com.example.test.bean.ProjectBean;
import com.example.test.bean.UserBean;
import com.example.test.mapper.ProjectMapper;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import org.assertj.core.error.ShouldBeAfterYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //将DAO注入Service层
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public UserBean loginIn(String name, String password) {
        return null;
    }

    @Override
    public List<ProjectBean> getProjectAll() {
        return projectMapper.getProInfoAll();
    }

    @Override
    public void insertProject() {
        Date date = new Date();
        Date date1 = new Date();
        Timestamp s1 = new Timestamp(date.getTime());
        Timestamp s2 = new Timestamp((date1.getTime()));
        ProjectBean projectBean = new ProjectBean("3", "2",
                "2", "项目2", 1,
                  date, date1);
        System.out.println("test");
        projectMapper.insertProject(projectBean);
        System.out.println("test done");
    }
}