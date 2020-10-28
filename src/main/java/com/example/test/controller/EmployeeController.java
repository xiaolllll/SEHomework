package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.ProjectBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.service.DataQueryService;
import com.example.test.service.EmployeeService;
import com.example.test.service.LoginService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class EmployeeController {

    //将Service注入Web层
    @Autowired
    DataQueryService dataQueryService;
    @Autowired
    EmployeeService employeeService;
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

    @RequestMapping(value = "/getAllEmpInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getAllEmpInfo() {
        System.out.println("test");
        List<EmployeeBean> list = dataQueryService.getAllEmployee();
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping(value = "/getSubTaskEmpDoSelf", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult getSubTaskEmpDoSelf(@RequestBody SubTaskBean subTaskBean) {
         EmployeeBean employeeBean= dataQueryService.getSubTaskEmployeeDoSelf(subTaskBean.getSubTaskId());
        if (employeeBean == null) {
            return JSONResult.errorMessage("该项目无人负责");
        } else {
            return JSONResult.ok(employeeBean);
        }
    }

    @RequestMapping(value = "/getProjectEmpInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult getProjectEmpInfo(@RequestBody ProjectBean projectBean) {
        List<EmployeeBean> list = dataQueryService.getProjectEmployee(projectBean.getProjectId());
        if (list == null) {
            return JSONResult.errorMessage("查询项目员工出错");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping(value = "/addEmp", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addEmp(@RequestBody EmployeeBean employeeBean) {
        String result=employeeService.addEmp(employeeBean);
        if(result.contains(ServiceUtil.SUCCESS)){
            return JSONResult.build(200,result,null);
        }else {
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping(value = "/modifyEmp", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult modifyEmp(@RequestBody EmployeeBean employeeBean) {
        String result=employeeService.modifyEmp(employeeBean);
        if(result.contains(ServiceUtil.SUCCESS)){
            return JSONResult.build(200,result,null);
        }else {
            return JSONResult.build(500,result,null);
        }
    }
}