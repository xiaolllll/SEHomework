package com.example.test.controller;

import com.example.test.bean.ProFinishInfoBean;
import com.example.test.bean.TaskFileListBean;
import com.example.test.service.TaskFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class TaskFileController {
    @Autowired
    TaskFileService taskFileService;

    @RequestMapping("/getFileList")
    @ResponseBody
    public JSONResult getFileList(HttpServletRequest req, @RequestBody String taskId) {
        List<String> result=taskFileService.getFileList(taskId);
        if(result == null){
            return JSONResult.errorMessage("无此任务信息");
        }else {
            System.out.println(result);
            return JSONResult.ok(result);
        }
    }

    @RequestMapping("/addTaskFileList")
    @ResponseBody
    public JSONResult addTaskFileList(HttpServletRequest req, @RequestBody TaskFileListBean taskFileListBean) {
        int result=taskFileService.addTaskFileList(taskFileListBean);
        if(result != 1){
            return JSONResult.errorMessage("添加失败");
        }else {
            System.out.println(result);
            return JSONResult.ok(result);
        }
    }
}
