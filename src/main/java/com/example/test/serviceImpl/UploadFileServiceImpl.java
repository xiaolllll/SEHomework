package com.example.test.serviceImpl;

import com.example.test.service.UploadFileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.io.IOException;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public String uploadFile(MultipartFile zipFile) {
        System.out.println("test");
        String targetFilePath = "D:\\test";
        String fileName = UUID.randomUUID().toString().replace("-", "");
        File targetFile = new File(targetFilePath + File.separator + fileName);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileOutputStream != null;
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
