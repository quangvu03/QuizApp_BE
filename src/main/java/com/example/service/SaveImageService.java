package com.example.service;

import jakarta.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Singleton
public class SaveImageService {

        private final String uploadDir = "src/main/resources/static/images";
        private final String baseUrl = "http://localhost:8001/images";

        public SaveImageService() {
            try {
                // Tạo thư mục nếu chưa tồn tại
                Files.createDirectories(Paths.get(uploadDir));
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload directory", e);
            }
        }

        public String saveFile(byte[] fileBytes, String originalFileName) throws IOException {
            // Tạo tên file duy nhất
            String fileExtension = originalFileName.contains(".")
                    ? originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
                    : "jpg";
            String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
            Path filePath = Paths.get(uploadDir, newFileName);

            // Lưu file
            Files.write(filePath, fileBytes);

            // Trả về URL của ảnh
            return baseUrl + "/" + newFileName;
        }
    }

