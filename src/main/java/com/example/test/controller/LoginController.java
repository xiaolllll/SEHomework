package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.UserBean;
import com.example.test.service.LoginService;
import com.example.test.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class LoginController {

    //将Service注入Web层
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public String show(){
        return "login";
    }

    @RequestMapping("/loginIn")
    @ResponseBody
    public JSONResult login(@RequestBody EmployeeBean employeeBean){
        System.out.println("test");
        System.out.println(employeeBean.getEmpId());
        System.out.println(employeeBean.getEmpPassword());
        EmployeeBean employeeBean1 =loginService.loginIn(employeeBean.getEmpId(), employeeBean.getEmpPassword());
        if(employeeBean1 == null){
            return JSONResult.errorMessage("用户名或密码错误");
        }
        else {
            return JSONResult.ok(employeeBean1);
        }
    }

    @RequestMapping("/")
    @ResponseBody
    public JSONResult changePwd(@RequestBody EmployeeBean employeeBean){
        System.out.println("test change pwd");
        System.out.println(employeeBean.getEmpId());
        System.out.println(employeeBean.getEmpPassword());
        EmployeeBean employeeBean1 =loginService.loginIn(employeeBean.getEmpId(), employeeBean.getEmpPassword());
        if(employeeBean1 == null){
            return JSONResult.errorMessage("用户名或密码错误");
        }
        else {
            return JSONResult.ok(employeeBean1);
        }
    }



}