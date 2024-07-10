package org.example.coffeeshopwebsite.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    void handleImageUpload(Object entity, MultipartFile file);
}
