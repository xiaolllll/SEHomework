package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.UserBean;
import com.example.test.service.LoginService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    //将Service注入Web层
    @Autowired
    LoginService loginService;


    @RequestMapping("/loginIn")
    public JSONResult login(String name,String password){
        EmployeeBean employeeBean=loginService.loginIn(name,password);
        if(employeeBean == null){
            return JSONResult.errorMsg("用户名或密码错误");
        }
        else {
            return JSONResult.ok(employeeBean);
        }
    }



}