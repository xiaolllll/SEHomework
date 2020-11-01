package com.example.test.controller;

import com.example.test.Jwt.JwtUtils;
import com.example.test.bean.ProFinishInfoBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.bean.TaskFileListBean;
import com.example.test.communication.updateFileListBean;
import com.example.test.mapper.SubTaskMapper;
import com.example.test.service.LogService;
import com.example.test.service.TaskFileService;
import com.example.test.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TaskFileController {
    @Autowired
    TaskFileService taskFileService;
    @Autowired
    TaskLogService taskLogService;
    @Autowired
    SubTaskMapper subTaskMapper;

    @RequestMapping("/getFileList")
    @ResponseBody
    public JSONResult getFileList(HttpServletRequest req, @RequestBody SubTaskBean subTaskBean) {
        System.out.println(subTaskBean.getSubTaskId());
        List<TaskFileListBean> result=taskFileService.getFileList(subTaskBean.getSubTaskId());
//        List<String> list = new ArrayList<>();
//        for (TaskFileListBean taskFileListBean: result) {
//            list.add(taskFileListBean.getFilePath());
//        }
        if (result.size() > 0) {
            System.out.println(result.get(0).getSubTaskId() + " " + result.get(0).getFilePath());
        } else {
            System.out.println("null");
        }
        if(result == null){
            return JSONResult.errorMessage("无此任务信息");
        }else {
            return JSONResult.ok(result);
        }
    }

    @RequestMapping("/getFileListByEmpIdTaskId")
    @ResponseBody
    public JSONResult getFileListByEmpIdTaskId(HttpServletRequest req, @RequestBody TaskFileListBean taskFileListBean) {
        System.out.println(taskFileListBean.getSubTaskId());
        List<TaskFileListBean> result=taskFileService.getFileListByEmpIdTaskId(taskFileListBean.getEmpId(), taskFileListBean.getSubTaskId());
        if(result == null){
            return JSONResult.errorMessage("无此任务信息");
        }else {
            return JSONResult.ok(result);
        }
    }

    @RequestMapping("/addTaskFileList")
    @ResponseBody
    public JSONResult addTaskFileList(HttpServletRequest req, @RequestBody TaskFileListBean taskFileListBean) {
        String empId = JwtUtils.analysis(req);
        taskFileListBean.setEmpId(empId);
        int result=taskFileService.addTaskFileList(taskFileListBean);
        System.out.println(result);
        if(result != 1){
            return JSONResult.errorMessage("添加失败");
        }else {
            System.out.println(result);
            SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(taskFileListBean.getSubTaskId());
            System.out.println("sout" + taskFileListBean.getSubTaskId());
            int count = subTaskBean.getHasFinishFileCount();
            if (count >= subTaskBean.getTotalFileCount()) {
                return JSONResult.errorMessage("全部文件已经提交了呐~");
            }
            TaskFileListBean t2=new TaskFileListBean();
            t2.setSubTaskId(taskFileListBean.getSubTaskId());
            t2.setFilePath(taskFileListBean.getFilePath());
            t2.setEmpId(empId);
            taskFileService.addTaskFileList(t2);
            subTaskBean.setHasFinishFileCount(count + 1);
            System.out.println(subTaskBean.getHasFinishFileCount());
            subTaskMapper.updateSubTask(subTaskBean);
            taskLogService.addTaskLog(taskFileListBean.getSubTaskId(), empId,
                    empId + "上传" + taskFileListBean.getFilePath() + "文件");
            return JSONResult.ok(result);
        }
    }

    @RequestMapping("/updateFileList")
    @ResponseBody
    public JSONResult updateFileList(HttpServletRequest req, @RequestBody updateFileListBean data) {
        String empId = JwtUtils.analysis(req);
        TaskFileListBean t1=new TaskFileListBean();
        t1.setSubTaskId(data.getSubTaskId());
        t1.setFilePath(data.getOldFilePath());

        TaskFileListBean t2=new TaskFileListBean();
        t2.setSubTaskId(data.getSubTaskId());
        t2.setFilePath(data.getNewFilePath());
        t2.setEmpId(empId);


        taskFileService.deleteTaskFileList(t1);

        int result=taskFileService.addTaskFileList(t2);
        if(result != 1){
            return JSONResult.errorMessage("添加失败");
        }else {
            System.out.println(result);
            taskLogService.addTaskLog(data.getSubTaskId(), empId,
                    empId + "将" + data.getOldFilePath() + "文件替换为" + data.getNewFilePath() + "文件");
            return JSONResult.ok(result);
        }
    }
}
