package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.service.DataQueryService;
import com.example.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class EmployeeController {

    //将Service注入Web层
    @Autowired
    DataQueryService dataQueryService;

    @RequestMapping(value = "/getEmpInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getEmpInfo(@RequestBody EmployeeBean employeeBean) {
        System.out.println("test");
        System.out.println(employeeBean.getEmpId());
        EmployeeBean employeeBean1 = dataQueryService.getEmployee(employeeBean.getEmpId());
        if (employeeBean1 == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(employeeBean1);
        }
    }
}