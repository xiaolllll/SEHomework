package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.service.DataQueryService;
import com.example.test.service.LoginService;
import com.example.test.service.SubTaskService;
import com.example.test.service.TaskLogService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public JSONResult outSourcingApply(@RequestBody ApplyTask applyTask) {
        System.out.println("test");
        System.out.println(applyTask.getApplicantID());
        String msg = subTaskService.outSourcingApply(applyTask.ApplicantID, applyTask.getHelpersID(),
                applyTask.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(msg);
        }
    }

    @RequestMapping("/outSourcingHandover")
    @ResponseBody
    public JSONResult outSourcingHandover(@RequestBody ApplyTaskHandOver applyTaskHandOver) {
        System.out.println("test");
        System.out.println(applyTaskHandOver.getApplicantID());
        String msg = subTaskService.outSourcingHandover(applyTaskHandOver.ApplicantID, applyTaskHandOver.getHelpersID(),
                applyTaskHandOver.getSubTaskId(), applyTaskHandOver.taskOutSourceEndTime);
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            return JSONResult.ok(msg);
        }
        //TODO 发送通知：外包申请
    }

    @RequestMapping("/outSourcingRecovery")
    @ResponseBody
    public JSONResult outSourcingRecovery(@RequestBody ApplyTask applyTask) {
        System.out.println("test");
        System.out.println(applyTask.getApplicantID());
        String msg = subTaskService.outSourcingRecovery(applyTask.ApplicantID, applyTask.getHelpersID(),
                applyTask.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            return JSONResult.ok(msg);
        }
    }

    @RequestMapping("/subTaskCompleteApply")
    @ResponseBody
    public JSONResult subTaskCompleteApply(@RequestBody SubTaskBean subTaskBean) {
        System.out.println("test");
        System.out.println(subTaskBean.getSubTaskInProjectId());
        String msg = subTaskService.subTaskCompleteApply(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            return JSONResult.ok(msg);
        }
    }

    @RequestMapping("/subTaskCompleteConclusion")
    @ResponseBody
    public JSONResult subTaskCompleteConclusion(@RequestBody SubTaskBean subTaskBean) {
        System.out.println("test");
        System.out.println(subTaskBean.getSubTaskInProjectId());
        String msg = subTaskService.subTaskCompleteConclusion(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            return JSONResult.ok(msg);
        }
    }


    @RequestMapping("/subTaskCompleteRejection")
    @ResponseBody
    public JSONResult subTaskCompleteRejection(@RequestBody SubTaskBean subTaskBean) {
        System.out.println("test");
        System.out.println(subTaskBean.getSubTaskInProjectId());
        String msg = subTaskService.subTaskCompleteConclusion(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            return JSONResult.ok(msg);
        }
    }
}