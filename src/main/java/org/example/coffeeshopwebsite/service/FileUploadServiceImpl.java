package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {
        private final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

        @Override
        public void handleImageUpload(Object entity, MultipartFile file) {
            String fileName = file.getOriginalFilename();
            String uploadDir = "./src/main/resources/static/user/images/";
            Path uploadPath = Paths.get(uploadDir); // Tao mot Object Path tu duong dan tuong doi

            try {
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                assert fileName != null;
                Path filePath = uploadPath.resolve(fileName); // resolve ket hop duong dan uploadPath voi fileName de tao ra duong dan hoan chinh luu voi ten file

                if (!Files.exists(filePath)) {
                    Files.copy(file.getInputStream(), filePath);
                }
                /*Neu Object entity la instance cua Product hoac Article thi ep kieu theo instance do*/
                if (entity instanceof Product)
                    ((Product) entity).setImage(fileName);
//            else if (entity instanceof Article)
//                ((Article) entity).setImage(fileName);
            } catch (Exception e) {
                logger.error("Failed to save image", e);
            }
        }
}
