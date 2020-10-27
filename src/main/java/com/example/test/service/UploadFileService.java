package com.example.test.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    int uploadFile(MultipartFile zipFile);
}
