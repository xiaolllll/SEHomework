package com.example.test.controller;

import com.example.test.Jwt.JwtUtils;
import com.example.test.bean.EmployeeBean;
import com.example.test.bean.ProjectBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.communication.*;
import com.example.test.mapper.ProjectMapper;
import com.example.test.mapper.SubTaskMapper;
import com.example.test.service.*;
import com.example.test.util.NotifyUtil;
import com.example.test.util.ServiceUtil;
import com.example.test.websocket.WebSocketServer;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private SubTaskService subTaskService;
    @RequestMapping("/addSubTask")
    @ResponseBody
    public JSONResult addSubTask(HttpServletRequest request, @RequestBody addSubTaskBean data) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.addSubTask(data.getTaskBean(),data.getLeadingPath(),data.getSucceedingPath(),data.isChain());
        if(result.contains(ServiceUtil.SUCCESS)){
            String PId=data.getTaskBean().getSubTaskInProjectId();
            String TId=result.replace(ServiceUtil.SUCCESS,"");
            logService.addLog(PId,userId,"新增了子任务"+TId);
            taskLogService.addTaskLog(TId,userId,"任务创建");
            return JSONResult.build(200,result,TId);
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

            EmployeeBean employeeBean=dataQueryService.getSubTaskEmployeeDoSelf(SubTaskID);
            if(employeeBean!=null){
                notifyService.addNotify(employeeBean.getEmpId(),userId,"负责的子任务"+SubTaskID+"已被删除", NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的子任务"+SubTaskID+"已被删除",employeeBean.getEmpId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/modifySubTask")
    @ResponseBody
    public JSONResult modifySubTask(HttpServletRequest request, @RequestBody SubTaskBean subTaskBean) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.modifySubTask(subTaskBean);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(subTaskBean.getSubTaskInProjectId(),userId,"修改了子任务"+subTaskBean.getSubTaskId());
            taskLogService.addTaskLog(subTaskBean.getSubTaskId(),userId,"修改了此任务");

            EmployeeBean employeeBean=dataQueryService.getSubTaskEmployeeDoSelf(subTaskBean.getSubTaskId());
            if(employeeBean!=null){
                notifyService.addNotify(employeeBean.getEmpId(),userId,"负责的子任务"+subTaskBean.getSubTaskId()+"已被修改", NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的子任务"+subTaskBean.getSubTaskId()+"已被修改",employeeBean.getEmpId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/restartSubTask")
    @ResponseBody
    public JSONResult restartSubTask(HttpServletRequest request, @RequestBody restartSubTaskBean data) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.restartSubTask(data.getSubTaskID(),data.isChain());
        if(result.contains(ServiceUtil.SUCCESS)){
            String PId=dataQueryService.getSubTask(data.getSubTaskID()).getSubTaskInProjectId();
            logService.addLog(PId,userId,"重启了任务"+data.getSubTaskID());
            taskLogService.addTaskLog(data.getSubTaskID(),userId,"被重启");

            EmployeeBean employeeBean=dataQueryService.getSubTaskEmployeeDoSelf(data.getSubTaskID());
            if(employeeBean!=null){
                notifyService.addNotify(employeeBean.getEmpId(),userId,"负责的子任务"+data.getSubTaskID()+"已被重启", NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的子任务"+data.getSubTaskID()+"已被重启",employeeBean.getEmpId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
            String PId=dataQueryService.getSubTask(SubTaskID).getSubTaskInProjectId();
            logService.addLog(PId,userId,"强制结束了任务"+SubTaskID);
            taskLogService.addTaskLog(SubTaskID,userId,"被强制结束");

            EmployeeBean employeeBean=dataQueryService.getSubTaskEmployeeDoSelf(SubTaskID);
            if(employeeBean!=null){
                notifyService.addNotify(employeeBean.getEmpId(),userId,"负责的子任务"+SubTaskID+"已强制结束", NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的子任务"+SubTaskID+"已强制结束",employeeBean.getEmpId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


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
            String PId=dataQueryService.getSubTask(data.getSubTaskID()).getSubTaskInProjectId();
            logService.addLog(PId,userId,"将子任务"+data.getSubTaskID()+"分配给了"+data.getEmpID());
            taskLogService.addTaskLog(data.getSubTaskID(),data.getEmpID(),"任务被分配给"+data.getEmpID());
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/projectCompleteApply")
    @ResponseBody
    public JSONResult projectCompleteApply(HttpServletRequest request, @RequestBody String projectID) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.projectCompleteApply(projectID);
        if(result.contains(ServiceUtil.SUCCESS)){
//            String MId=dataQueryService.getProject(projectID).getProManagerId();
//           notifyService.addNotify(MId,userId,"项目"+projectID+"申请结项",NotifyUtil.NO_REPLY);
//            try {
//                WebSocketServer.sendInfo("updateNotify",MId);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/createProject")
    @ResponseBody
    public JSONResult createProject(HttpServletRequest request, @RequestBody ProjectBean projectBean) {
        System.out.println("createProject");
        String userId = JwtUtils.analysis(request);
        String result=projectService.createProject(projectBean);
        if(result.contains(ServiceUtil.SUCCESS)){
            String PId=result.replace(ServiceUtil.SUCCESS,"");
            return JSONResult.build(200,result,PId);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/enableProject")
    @ResponseBody
    public JSONResult enableProject(HttpServletRequest request, @RequestBody String projectID) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.enableProject(projectID);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(projectID,userId,"项目启动");
            String managerID=dataQueryService.getProject(projectID).getProManagerId();
            if(managerID!=null){
                notifyService.addNotify(managerID,userId,"负责的项目"+projectID+"已启动",NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的项目"+projectID+"已启动",managerID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/restartProject")
    @ResponseBody
    public JSONResult restartProject(HttpServletRequest request, @RequestBody String projectID) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.restartProject(projectID);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(projectID,userId,"项目重启");
            String managerID=dataQueryService.getProject(projectID).getProManagerId();
            if(managerID!=null){
                notifyService.addNotify(managerID,userId,"负责的项目"+projectID+"已重启",NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的项目"+projectID+"已重启",managerID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/abandonProject")
    @ResponseBody
    public JSONResult abandonProject(HttpServletRequest request, @RequestBody String projectID) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.abandonProject(projectID);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(projectID,userId,"项目废弃");
            String managerID=dataQueryService.getProject(projectID).getProManagerId();
            if(managerID!=null){
                notifyService.addNotify(managerID,userId,"负责的项目"+projectID+"已废弃",NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的项目"+projectID+"已废弃",managerID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/completeProject")
    @ResponseBody
    public JSONResult completeProject(HttpServletRequest request, @RequestBody String projectID) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.completeProject(projectID);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(projectID,userId,"项目已完成");
            String managerID=dataQueryService.getProject(projectID).getProManagerId();
            if(managerID!=null){
                notifyService.addNotify(managerID,userId,"负责的项目"+projectID+"已完成",NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的项目"+projectID+"已完成",managerID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/modifyProject")
    @ResponseBody
    public JSONResult modifyProject(HttpServletRequest request, @RequestBody ProjectBean projectBean) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.modifyProject(projectBean);
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(projectBean.getProjectId(),userId,"项目已修改");
            String managerID=dataQueryService.getProject(projectBean.getProjectId()).getProManagerId();
            if(managerID!=null){
                notifyService.addNotify(managerID,userId,"负责的项目"+projectBean.getProjectId()+"已修改",NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("负责的项目"+projectBean.getProjectId()+"已修改",managerID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/setProjectManager")
    @ResponseBody
    public JSONResult setProjectManager(HttpServletRequest request, @RequestBody setProjectManagerBean data) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.setProjectManager(data.getProjectID(),data.getEmpID());
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(data.getProjectID(),userId,"项目已修改被交给"+data.getEmpID());
                notifyService.addNotify(data.getEmpID(),userId,"被任命为"+data.getEmpID()+"的负责人",NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("被任命为"+data.getEmpID()+"的负责人",data.getEmpID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/addProjectPerson")
    @ResponseBody
    public JSONResult addProjectPerson(HttpServletRequest request, @RequestBody addProjectPersonBean data) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.addProjectPerson(data.getProjectID(),data.getEmpIDs());
        if(result.contains(ServiceUtil.SUCCESS)){
            for(String EmpID:data.getEmpIDs()) {
                logService.addLog(data.getProjectID(), userId, EmpID + "加入项目");
                notifyService.addNotify(EmpID, userId, "你已加入到项目" + data.getProjectID(), NotifyUtil.NO_REPLY);
                try {
                    WebSocketServer.sendInfo("你已加入到项目" + data.getProjectID(), EmpID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/deleteProjectPerson")
    @ResponseBody
    public JSONResult deleteProjectPerson(HttpServletRequest request, @RequestBody deleteProjectPersonBean data) {
        String userId = JwtUtils.analysis(request);
        String result=projectService.deleteProjectPerson(data.getProjectID(),data.getEmpID());
        if(result.contains(ServiceUtil.SUCCESS)){
            logService.addLog(data.getProjectID(),userId,data.getEmpID()+"被移除项目");
            notifyService.addNotify(data.getEmpID(),userId,"你已经被移出项目"+data.getProjectID(),NotifyUtil.NO_REPLY);
            try {
                WebSocketServer.sendInfo("你已经被移出项目"+data.getProjectID(),data.getEmpID());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }
}
