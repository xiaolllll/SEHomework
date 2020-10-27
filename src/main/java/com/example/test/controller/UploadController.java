package com.example.test.controller;

import ch.qos.logback.core.util.FileUtil;
import com.example.test.service.UploadFileService;
import com.example.test.serviceImpl.UploadFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class UploadController {
    @Autowired
    private UploadFileService uploadFileService;


//    @ResponseBody
//    @RequestMapping("/upload")
//    public String uploadFileTest(@RequestParam("uploadFile") MultipartFile zipFile) {
//        System.out.println("test upload");
//        return uploadFileService.uploadFile(zipFile);
//    }

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestBody MultipartFile file,  HttpServletRequest req) throws IOException {
        System.out.println("test upload");
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/upload") + format;
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder,newName));
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + format + newName;
        System.out.println(" test " + format +  newName);
        return format + newName;
    }

}
