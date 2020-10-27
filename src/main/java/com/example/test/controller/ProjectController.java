package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.ProjectBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProjectController {
    @Autowired
    DataQueryService dataQueryService;

    @RequestMapping("/getRunProject")
    @ResponseBody
    public JSONResult getRunProject(@RequestBody String empID) {
        System.out.println("test");
        System.out.println(empID);
        List<ProjectBean> list = dataQueryService.getRunProject(empID);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getCompletedProject")
    @ResponseBody
    public JSONResult getCompletedProject(@RequestBody String empID) {
        System.out.println("test");
        System.out.println(empID);
        List<ProjectBean> list = dataQueryService.getCompletedProject(empID);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }
}
