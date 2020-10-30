package com.example.test.controller;

import com.example.test.Jwt.JwtUtils;
import com.example.test.bean.*;
import com.example.test.communication.getProjectAndSubTaskBean;
import com.example.test.service.*;
import com.example.test.util.NotifyUtil;
import com.example.test.util.ServiceUtil;
import com.example.test.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ApplyTask {
    String applicantID;
    String helpersID;
    String subTaskId;

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public String getHelpersID() {
        return helpersID;
    }

    public void setHelpersID(String helpersID) {
        this.helpersID = helpersID;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }
}

class ApplyTaskHandOver {
    String applicantID;
    String helpersID;
    String subTaskId;
    Date taskOutSourceEndTime;

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public String getHelpersID() {
        return helpersID;
    }

    public void setHelpersID(String helpersID) {
        this.helpersID = helpersID;
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

    @RequestMapping("/getEmpDoingProject")
    @ResponseBody
    public JSONResult getEmpDoingProject(HttpServletRequest request) {
        System.out.println("test");
        String userId = JwtUtils.analysis(request);
        System.out.println(userId);
        List<ProjectBean> list = dataQueryService.getRunProject(userId);
//        System.out.println(list.size());
//        if (list.size() > 0) {
//            System.out.println(list.get(0).getProjectDesc());
//        }
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }


    @RequestMapping("/getEmpDoingProjectEmpId")
    @ResponseBody
    public JSONResult getEmpDoingProjectEmpId(HttpServletRequest request, @RequestBody EmployeeBean employeeBean) {
        System.out.println("test");
        String userId = employeeBean.getEmpId();
        System.out.println(userId);
        List<ProjectBean> list = dataQueryService.getRunProject(userId);
//        System.out.println(list.size());
//        if (list.size() > 0) {
//            System.out.println(list.get(0).getProjectDesc());
//        }
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getEmpDoingProjectAndSubTaskEmpId")
    @ResponseBody
    public JSONResult getEmpDoingProjectAndSubTaskEmpId(HttpServletRequest request, @RequestBody EmployeeBean employeeBean) {
        String userId = employeeBean.getEmpId();
        List<getProjectAndSubTaskBean> resultList=new ArrayList<getProjectAndSubTaskBean>();
        List<ProjectBean> list = dataQueryService.getRunProject(userId);
        for (ProjectBean pt:list){
            List<SubTaskBean> sublist = dataQueryService.getProjectSubTask(pt.getProjectId());
            getProjectAndSubTaskBean result=new getProjectAndSubTaskBean();
            result.setProjectBean(pt);
            result.setList(sublist);
            resultList.add(result);
        }
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(resultList);
        }
    }

    @RequestMapping("/getProjectSubTask")
    @ResponseBody
    public JSONResult getProjectSubTask(@RequestBody ProjectBean projectBean) {
        List<SubTaskBean> list = dataQueryService.getProjectSubTask(projectBean.getProjectId());
        if (list == null) {
            return JSONResult.errorMessage("查询项目中的任务出错");
        } else {
            return JSONResult.ok(list);
        }
    }

//    @RequestMapping("/getProjectAndSubTask")
//    @ResponseBody
//    public JSONResult getProjectAndSubTask() {
//        List<ProjectBean>
//        ProjectBean p1=dataQueryService.getProject(projectBean.getProjectId());
//        List<SubTaskBean> list = dataQueryService.getProjectSubTask(projectBean.getProjectId());
//        getProjectAndSubTaskBean result=new getProjectAndSubTaskBean();
//        result.setProjectBean(p1);
//        result.setList(list);
//        if (list == null) {
//            return JSONResult.errorMessage("查询项目中的任务出错");
//        } else {
//            return JSONResult.ok(result);
//        }
//    }

    @RequestMapping("/getTaskByProIdEmpId")
    @ResponseBody
    public JSONResult getTaskByProIdEmpId(@RequestBody ProFinishInfoBean proFinishInfoBean) {
        List<TaskFinishInfoBean> list = dataQueryService.getTaskFinishInfoByProIDEmpID(proFinishInfoBean.getProjectId(),
                proFinishInfoBean.getEmpId());
        if (list == null) {
            return JSONResult.errorMessage("查询出错");
        } else {
            return JSONResult.ok(list);
        }
    }


    @RequestMapping("/getEmpHasDoneProject")
    @ResponseBody
    public JSONResult getEmpHasDoneProject(HttpServletRequest request) {
        System.out.println("test");
        String userId = JwtUtils.analysis(request);
        System.out.println(userId);
        List<ProjectBean> list = dataQueryService.getCompletedProject(userId);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getEmpHasDoneProjectEmpId")
    @ResponseBody
    public JSONResult getEmpHasDoneProjectEmpId(HttpServletRequest request, @RequestBody EmployeeBean employeeBean) {
        System.out.println("test");
        String userId = employeeBean.getEmpId();
        System.out.println(userId);
        List<ProjectBean> list = dataQueryService.getCompletedProject(userId);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getEmpDoingTask")
    @ResponseBody
    public JSONResult getEmpDoingTask(HttpServletRequest request) {
        System.out.println("test");
        String userId = JwtUtils.analysis(request);
        System.out.println(userId);
        List<SubTaskBean> list = dataQueryService.getTaskInfoByEmpIdDoing(userId);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getEmpDoingTaskEmpId")
    @ResponseBody
    public JSONResult getEmpDoingTaskEmpId(HttpServletRequest request, @RequestBody EmployeeBean employeeBean) {
        System.out.println("test");
        String userId = employeeBean.getEmpId();
        System.out.println(userId);
        List<SubTaskBean> list = dataQueryService.getTaskInfoByEmpIdDoing(userId);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getEmpHasDoneTask")
    @ResponseBody
    public JSONResult getEmpHasDoneTask(HttpServletRequest request) {
        System.out.println("test");
        String userId = JwtUtils.analysis(request);
        System.out.println(userId);
        List<SubTaskBean> list = dataQueryService.getTaskInfoByEmpHasDone(userId);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/getEmpHasDoneTaskEmpId")
    @ResponseBody
    public JSONResult getEmpHasDoneTaskEmpId(HttpServletRequest request, @RequestBody EmployeeBean employeeBean) {
        System.out.println("test");
        String userId = employeeBean.getEmpId();
        System.out.println(userId);
        List<SubTaskBean> list = dataQueryService.getTaskInfoByEmpHasDone(userId);
        if (list == null) {
            return JSONResult.errorMessage("无此用户名");
        } else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/outSourcingApply")
    @ResponseBody
    public JSONResult outSourcingApply(HttpServletRequest request, @RequestBody ApplyTask applyTask) throws IOException {
//        System.out.println("test");
        String userId = JwtUtils.analysis(request);
        System.out.println("test" + userId);
        String msg = subTaskService.outSourcingApply(userId, applyTask.getHelpersID(),
                applyTask.getSubTaskId());
        if (msg == null) {
           return JSONResult.errorMessage("无此用户名");
        } else {
            notifyService.addNotify(userId, applyTask.helpersID, "申请外包任务" + applyTask.subTaskId, NotifyUtil.APPLY_OUT_SOURCE);
            String res = userId + "申请外包任务" + applyTask.subTaskId;
            WebSocketServer.sendInfo(res, applyTask.getHelpersID());
            return JSONResult.ok(msg);
        }
    }

    //由管理员发送
    @RequestMapping("/outSourcingHandover")
    @ResponseBody
    public JSONResult outSourcingHandover(HttpServletRequest request, @RequestBody ApplyTaskHandOver applyTaskHandOver) throws IOException {
        System.out.println("test");
        System.out.println(applyTaskHandOver.getApplicantID());
        String userId = JwtUtils.analysis(request);
        System.out.println(applyTaskHandOver.getApplicantID());
        String msg = subTaskService.outSourcingHandover(applyTaskHandOver.getApplicantID(), applyTaskHandOver.getHelpersID(),
                applyTaskHandOver.getSubTaskId(), applyTaskHandOver.getTaskOutSourceEndTime());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else if (msg.contains(ServiceUtil.SUCCESS)){
            String PId= dataQueryService.getSubTask(applyTaskHandOver.subTaskId).getSubTaskInProject();
            logService.addLog(PId, applyTaskHandOver.getApplicantID(), "申请" + applyTaskHandOver.helpersID +
                    "外包"+ applyTaskHandOver.subTaskId +"子任务" );
            notifyService.addNotify(applyTaskHandOver.applicantID, applyTaskHandOver.helpersID,
                    "外包任务" + applyTaskHandOver.subTaskId + "申请已完成", NotifyUtil.APPLY_OUT_SOURCE_DONE);
            String res;
            res = applyTaskHandOver.applicantID + "申请" + applyTaskHandOver.helpersID + "外包"+ applyTaskHandOver.subTaskId +"子任务成功";
            WebSocketServer.sendInfo(res, applyTaskHandOver.applicantID);
            WebSocketServer.sendInfo(res, applyTaskHandOver.helpersID);
            return JSONResult.ok(msg);
        } else {
            return JSONResult.errorMessage(msg);
        }
    }

    @RequestMapping("/outSourcingRecovery")
    @ResponseBody
    public JSONResult outSourcingRecovery(HttpServletRequest request, @RequestBody ApplyTask applyTask) {
        System.out.println("test");
        String userId = JwtUtils.analysis(request);
        String msg = subTaskService.outSourcingRecovery(userId, applyTask.getHelpersID(),
                applyTask.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else if (msg.contains(ServiceUtil.SUCCESS)) {
            String PId= dataQueryService.getSubTask(applyTask.getSubTaskId()).getSubTaskInProject();
            logService.addLog(PId, userId, "申请回收" +
                     applyTask.getSubTaskId() +"子任务" );
            notifyService.addNotify(userId, applyTask.getHelpersID(),
                    "外包任务" + applyTask.getHelpersID() + "申请回收", NotifyUtil.APPLY_OUT_SOURCE_RECOVERY);
            try {
                String res = userId + "将外包任务" + applyTask.getHelpersID() + "申请回收";
                WebSocketServer.sendInfo(res, applyTask.getHelpersID());
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
        String userId = JwtUtils.analysis(request);
        String msg = subTaskService.subTaskCompleteApply(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else if (msg.contains(ServiceUtil.SUCCESS)) {
            //发送给这个项目的管理员
            SubTaskBean taskBean = dataQueryService.getSubTask(subTaskBean.getSubTaskId());
            String proId = taskBean.getSubTaskInProject();
            List<EmployeeBean> list = dataQueryService.getProjectEmployee(proId);
            for (EmployeeBean employeeBean : list) {
                if (employeeBean.getEmpType() == 0) { //管理员
                    notifyService.addNotify(userId, employeeBean.getEmpId(),
                            "任务" + subTaskBean.getSubTaskId() + "申请完成", NotifyUtil.TASK_DONE);
                    try {
                        String res = userId + "将任务" + subTaskBean.getSubTaskId() + "申请完成";
                        System.out.println("res "  +res);
                        WebSocketServer.sendInfo(res, employeeBean.getEmpId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
        System.out.println(subTaskBean.getSubTaskInProject());
        String userId = JwtUtils.analysis(request);
        String msg = subTaskService.subTaskCompleteConclusion(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            EmployeeBean receiver = dataQueryService.getSubTaskEmployeeDoSelf(subTaskBean.getSubTaskId());
            notifyService.addNotify(userId, receiver.getEmpId(),
                    "任务" + subTaskBean.getSubTaskId() + "完成已同意", NotifyUtil.TASK_DONE_AGREE);
            try {
                String res = userId + "将任务" + subTaskBean.getSubTaskId() + "完成已同意";
                System.out.println("res "  +res);
                WebSocketServer.sendInfo(res, receiver.getEmpId());
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
        System.out.println(subTaskBean.getSubTaskInProject());
        String userId = JwtUtils.analysis(request);
        String msg = subTaskService.subTaskCompleteConclusion(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            EmployeeBean receiver = dataQueryService.getSubTaskEmployeeDoSelf(subTaskBean.getSubTaskId());
            notifyService.addNotify(userId, receiver.getEmpId(),
                    "任务" + subTaskBean.getSubTaskId() + "完成已拒绝", NotifyUtil.TASK_DONE_REFUSE);
            try {
                String res = userId + "将任务" + subTaskBean.getSubTaskId() + "完成已拒绝";
                WebSocketServer.sendInfo(res, receiver.getEmpId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return JSONResult.ok(msg);
        }
    }

    @RequestMapping("/getBeforeTaskId")
    @ResponseBody
    public JSONResult getBeforeTaskId(HttpServletRequest request, @RequestBody SubTaskBean subTaskBean) {
        System.out.println("test");
        System.out.println(subTaskBean.getSubTaskInProject());
        String userId = JwtUtils.analysis(request);
        List<String> msg = subTaskService.getBeforeTaskId(subTaskBean.getSubTaskId());
        if (msg == null) {
            return JSONResult.errorMessage("出现异常");
        } else {
            return JSONResult.ok(msg);
        }
    }

}