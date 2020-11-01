package com.example.test.controller;

import com.example.test.bean.SubTaskBean;
import com.example.test.mapper.SubTaskMapper;
import com.example.test.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

class TempFile {
    private String subTaskId;
    private MultipartFile file;

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

@RestController
@CrossOrigin
public class UploadController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private SubTaskMapper subTaskMapper;


//    @ResponseBody
//    @RequestMapping("/upload")
//    public String uploadFileTest(@RequestParam("uploadFile") MultipartFile zipFile) {
//        System.out.println("test upload");
//        return uploadFileService.uploadFile(zipFile);
//    }

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(TempFile file,  HttpServletRequest req) throws IOException {
        if (file == null) {
            return "文件提交失败！";
        }
        System.out.println("test upload");
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/upload") + format;
        System.out.println(realPath);
//        realPath = "D:\\javaweb\\SEHomework\\src\\main\\resources\\fileSet";
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getFile().getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.getFile().transferTo(new File(folder,newName));
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + format + newName;
        System.out.println(" test " + format +  newName);
        //todo
        SubTaskBean subTaskBean = subTaskMapper.getTaskInfoByProId(file.getSubTaskId());
        System.out.println("sout" + file.getSubTaskId());
        int count = subTaskBean.getHasFinishFileCount();
        if (count >= subTaskBean.getTotalFileCount()) {
            return "全部文件已经提交了呐~";
        }
        subTaskBean.setHasFinishFileCount(count + 1);
        System.out.println(subTaskBean.getHasFinishFileCount());
        subTaskMapper.updateSubTask(subTaskBean);
        return format + newName;
    }

}
