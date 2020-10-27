package com.example.test.controller;

import com.example.test.Jwt.JwtUtils;
import com.example.test.bean.EmployeeBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.communication.addSubTaskBean;
import com.example.test.communication.setSubTaskPersonBean;
import com.example.test.mapper.ProjectMapper;
import com.example.test.mapper.SubTaskMapper;
import com.example.test.service.DataQueryService;
import com.example.test.service.LogService;
import com.example.test.service.ProjectService;
import com.example.test.service.TaskLogService;
import com.example.test.util.ServiceUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private LogService logService;
    @Autowired
    private TaskLogService taskLogService;
    @Autowired
    private DataQueryService dataQueryService;
    @RequestMapping("/addSubTask")
    @ResponseBody
    public JSONResult addSubTask(HttpServletRequest request, @RequestBody addSubTaskBean data) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.addSubTask(data.getTaskBean(),data.getLeadingPath(),data.getSucceedingPath(),data.isChain());
        if(result.contains(ServiceUtil.SUCCESS)){
            String PId=data.getTaskBean().getSubTaskInProjectId();
            String TId=data.getTaskBean().getSubTaskId();
            logService.addLog(PId,userId,"新增了子任务"+data.getTaskBean().getSubTaskId());
            taskLogService.addTaskLog(TId,userId,"任务创建");
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/deleteSubTask")
    @ResponseBody
    public JSONResult deleteSubTask(HttpServletRequest request, @RequestBody String SubTaskID) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.deleteSubTask(SubTaskID);
        if(result.contains(ServiceUtil.SUCCESS)){
            String PId=dataQueryService.getSubTask(SubTaskID).getSubTaskInProjectId();

            logService.addLog(PId,userId,"删除了子任务"+SubTaskID);
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/modifySubTask")
    @ResponseBody
    public JSONResult deleteSubTask(HttpServletRequest request, @RequestBody SubTaskBean subTaskBean) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.modifySubTask(subTaskBean);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(subTaskBean.getSubTaskInProjectId(),userId,"修改了子任务"+subTaskBean.getSubTaskId());
            taskLogService.addTaskLog(subTaskBean.getSubTaskId(),userId,"修改了此任务");
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/forceCompleteSubTask")
    @ResponseBody
    public JSONResult forceCompleteSubTask(HttpServletRequest request, @RequestBody String SubTaskID) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.forceCompleteSubTask(SubTaskID);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(SubTaskID,userId,"强制结束了任务"+SubTaskID);
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/setSubTaskPerson")
    @ResponseBody
    public JSONResult setSubTaskPerson(HttpServletRequest request, @RequestBody setSubTaskPersonBean data) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.setSubTaskPerson(data.getSubTaskID(),data.getEmpID());
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(data.getSubTaskID(),userId,"强制结束了任务"+data.getSubTaskID());
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

}
