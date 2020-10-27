package com.example.test.controller;

import com.example.test.Jwt.JwtUtils;
import com.example.test.bean.EmployeeBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.service.*;
import com.example.test.util.NotifyUtil;
import com.example.test.util.ServiceUtil;
import com.example.test.websocket.WebSocketServer;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

class ApplyTask {
    String ApplicantID;
    String HelpersID;
    String subTaskId;

    public String getApplicantID() {
        return ApplicantID;
    }

    public void setApplicantID(String applicantID) {
        ApplicantID = applicantID;
    }

    public String getHelpersID() {
        return HelpersID;
    }

    public void setHelpersID(String helpersID) {
        HelpersID = helpersID;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }
}

class ApplyTaskHandOver {
    String ApplicantID;
    String HelpersID;
    String subTaskId;
    Date taskOutSourceEndTime;

    public String getApplicantID() {
        return ApplicantID;
    }

    public void setApplicantID(String applicantID) {
        ApplicantID = applicantID;
    }

    public String getHelpersID() {
        return HelpersID;
    }

    public void setHelpersID(String helpersID) {
        HelpersID = helpersID;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public Date getTaskOutSourceEndTime() {
        return taskOutSourceEndTime;
    }

    public void setTaskOutSourceEndTime(Date taskOutSourceEndTime) {
        this.taskOutSourceEndTime = taskOutSourceEndTime;
    }
}

@RestController
@CrossOrigin
public class TaskController {

    //将Service注入Web层
    @Autowired
    DataQueryService dataQueryService;
    @Autowired
    SubTaskService subTaskService;
    @Autowired
    private LogService logService;
    @Autowired
    private NotifyService notifyService;

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

    @RequestMapping("/outSourcingApply")
    @ResponseBody
    public JSONResult outSourcingApply(@RequestBody ApplyTask applyTask) throws IOException {
        System.out.println("test");
        System.out.println(applyTask.getApplicantID());
        String msg = subTaskService.outSourcingApply(applyTask.ApplicantID, applyTask.getHelpersID(),
                applyTask.getSubTaskId());
        if (msg == null) {
//            String PId= dataQueryService.getSubTask(applyTask.subTaskId).getSubTaskInProjectId();
//            logService.addLog(PId, applyTask.getApplicantID(), "申请" + applyTask.HelpersID +"外包""子任务" )
           notifyService.addNotify(applyTask.ApplicantID, applyTask.HelpersID, "申请外包任务" + applyTask.subTaskId, NotifyUtil.APPLY_OUT_SOURCE);
           WebSocketServer.sendInfo("updatedNotify", applyTask.ApplicantID);
           return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(msg);
        }
    }

    @RequestMapping("/outSourcingHandover")
    @ResponseBody
    public JSONResult outSourcingHandover(@RequestBody ApplyTaskHandOver applyTaskHandOver) throws IOException {
        System.out.println("test");
        System.out.println(applyTaskHandOver.getApplicantID());
        String msg = subTaskService.outSourcingHandover(applyTaskHandOver.ApplicantID, applyTaskHandOver.getHelpersID(),
                applyTaskHandOver.getSubTaskId(), applyTaskHandOver.taskOutSourceEndTime);
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else if (msg.contains(ServiceUtil.SUCCESS)){
            String PId= dataQueryService.getSubTask(applyTaskHandOver.subTaskId).getSubTaskInProjectId();
            logService.addLog(PId, applyTaskHandOver.getApplicantID(), "申请" + applyTaskHandOver.HelpersID +
                    "外包"+ applyTaskHandOver.subTaskId +"子任务" );
            notifyService.addNotify(applyTaskHandOver.ApplicantID, applyTaskHandOver.HelpersID,
                    "外包任务" + applyTaskHandOver.subTaskId + "申请已完成", NotifyUtil.APPLY_OUT_SOURCE_DONE);
            WebSocketServer.sendInfo("updatedNotify", applyTaskHandOver.ApplicantID);
            return JSONResult.ok(msg);
        } else {
            return JSONResult.errorMessage(msg);
        }
    }

    @RequestMapping("/outSourcingRecovery")
    @ResponseBody
    public JSONResult outSourcingRecovery(HttpServletRequest request, @RequestBody ApplyTask applyTask) {
        System.out.println("test");
        System.out.println(applyTask.getApplicantID());
        String msg = subTaskService.outSourcingRecovery(applyTask.ApplicantID, applyTask.getHelpersID(),
                applyTask.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else if (msg.contains(ServiceUtil.SUCCESS)) {
            String PId= dataQueryService.getSubTask(applyTask.subTaskId).getSubTaskInProjectId();
            logService.addLog(PId, applyTask.getApplicantID(), "申请回收" +
                     applyTask.subTaskId +"子任务" );
            notifyService.addNotify(applyTask.ApplicantID, applyTask.HelpersID,
                    "外包任务" + applyTask.subTaskId + "申请回收", NotifyUtil.APPLY_OUT_SOURCE_RECOVERY);
            try {
                WebSocketServer.sendInfo("updatedNotify", applyTask.ApplicantID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JSONResult.ok(msg);
        } else {
            return JSONResult.errorMessage(msg);
        }
    }

    @RequestMapping("/subTaskCompleteApply")
    @ResponseBody
    public JSONResult subTaskCompleteApply(HttpServletRequest request, @RequestBody SubTaskBean subTaskBean) {
        System.out.println("test");
        System.out.println(subTaskBean.getSubTaskInProjectId());
        String userId = JwtUtils.analysis(request);
        String msg = subTaskService.subTaskCompleteApply(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else if (msg.contains(ServiceUtil.SUCCESS)) {
            List<EmployeeBean> list = dataQueryService.getAllEmployee();
            //发送给这个项目的管理员
            for (EmployeeBean employeeBean : list) {
                if (employeeBean.getEmpType() == 0) { //管理员
                    notifyService.addNotify(userId, employeeBean.getEmpId(),
                            "任务" + subTaskBean.getSubTaskId() + "申请完成", NotifyUtil.TASK_DONE);
                }
            }
            try {
                WebSocketServer.sendInfo("updatedNotify", userId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JSONResult.ok(msg);
        } else {
            return JSONResult.errorMessage(msg);
        }
    }

    @RequestMapping("/subTaskCompleteConclusion")
    @ResponseBody
    public JSONResult subTaskCompleteConclusion(HttpServletRequest request, @RequestBody SubTaskBean subTaskBean) {
        System.out.println("test");
        System.out.println(subTaskBean.getSubTaskInProjectId());
        String userId = JwtUtils.analysis(request);
        String msg = subTaskService.subTaskCompleteConclusion(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            EmployeeBean receiver = dataQueryService.getSubTaskEmployeeDoSelf(subTaskBean.getSubTaskId());
            notifyService.addNotify(userId, receiver.getEmpId(),
                    "外包任务" + subTaskBean.getSubTaskId() + "申请回收", NotifyUtil.TASK_DONE_AGREE);
            try {
                WebSocketServer.sendInfo("updatedNotify", receiver.getEmpId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JSONResult.ok(msg);
        }
    }


    @RequestMapping("/subTaskCompleteRejection")
    @ResponseBody
    public JSONResult subTaskCompleteRejection(HttpServletRequest request, @RequestBody SubTaskBean subTaskBean) {
        System.out.println("test");
        System.out.println(subTaskBean.getSubTaskInProjectId());
        String userId = JwtUtils.analysis(request);
        String msg = subTaskService.subTaskCompleteConclusion(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            EmployeeBean receiver = dataQueryService.getSubTaskEmployeeDoSelf(subTaskBean.getSubTaskId());
            notifyService.addNotify(userId, receiver.getEmpId(),
                    "外包任务" + subTaskBean.getSubTaskId() + "申请回收", NotifyUtil.TASK_DONE_REFUSE);
            try {
                WebSocketServer.sendInfo("updatedNotify", receiver.getEmpId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JSONResult.ok(msg);
        }
    }
}