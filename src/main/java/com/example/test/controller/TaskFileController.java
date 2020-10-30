package com.example.test.controller;

import com.example.test.bean.ProFinishInfoBean;
import com.example.test.bean.SubTaskBean;
import com.example.test.bean.TaskFileListBean;
import com.example.test.communication.updateFileListBean;
import com.example.test.service.TaskFileService;
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

    @RequestMapping("/getFileList")
    @ResponseBody
    public JSONResult getFileList(HttpServletRequest req, @RequestBody SubTaskBean subTaskBean) {
        System.out.println(subTaskBean.getSubTaskId());
        List<TaskFileListBean> result=taskFileService.getFileList(subTaskBean.getSubTaskId());
        List<String> list = new ArrayList<>();
        for (TaskFileListBean taskFileListBean: result) {
            list.add(taskFileListBean.getFilePath());
        }
        if (result.size() > 0) {
            System.out.println(result.get(0).getSubTaskId() + " " + result.get(0).getFilePath());
        } else {
            System.out.println("null");
        }
        if(result == null){
            return JSONResult.errorMessage("无此任务信息");
        }else {
            return JSONResult.ok(list);
        }
    }

    @RequestMapping("/addTaskFileList")
    @ResponseBody
    public JSONResult addTaskFileList(HttpServletRequest req, @RequestBody TaskFileListBean taskFileListBean) {
        int result=taskFileService.addTaskFileList(taskFileListBean);
        System.out.println(result);
        if(result != 1){
            return JSONResult.errorMessage("添加失败");
        }else {
            System.out.println(result);
            return JSONResult.ok(result);
        }
    }

    @RequestMapping("/updateFileList")
    @ResponseBody
    public JSONResult updateFileList(HttpServletRequest req, @RequestBody updateFileListBean data) {
        TaskFileListBean t1=new TaskFileListBean();
        t1.setSubTaskId(data.getSubTaskId());
        t1.setFilePath(data.getOldFilePath());

        TaskFileListBean t2=new TaskFileListBean();
        t2.setSubTaskId(data.getSubTaskId());
        t2.setFilePath(data.getNewFilePath());

        taskFileService.deleteTaskFileList(t1);

        int result=taskFileService.addTaskFileList(t2);
        if(result != 1){
            return JSONResult.errorMessage("添加失败");
        }else {
            System.out.println(result);
            return JSONResult.ok(result);
        }
    }
}
