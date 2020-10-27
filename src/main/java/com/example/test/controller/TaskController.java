package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.service.DataQueryService;
import com.example.test.service.LoginService;
import com.example.test.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    //将Service注入Web层
    @Autowired
    DataQueryService dataQueryService;

    @RequestMapping("/getEmpDoingTask")
    @ResponseBody
    public JSONResult getEmpDoingTask(@RequestBody EmployeeBean employeeBean) {
        System.out.println("test");
        System.out.println(employeeBean.getEmpId());
        List<SubTaskBean> list = dataQueryService.getTaskInfoByEmpIdDoing(employeeBean.getEmpId());
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getEmpHasDoneTask")
    @ResponseBody
    public JSONResult getEmpHasDoneTask(@RequestBody EmployeeBean employeeBean) {
        System.out.println("test");
        System.out.println(employeeBean.getEmpId());
        List<SubTaskBean> list = dataQueryService.getTaskInfoByEmpHasDone(employeeBean.getEmpId());
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }
}