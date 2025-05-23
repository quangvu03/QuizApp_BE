package com.example.service;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SaveImageService {

    private static final Logger LOG = LoggerFactory.getLogger(SaveImageService.class);
    private final String uploadDir;
    private final String baseUrl;

    public SaveImageService(@Value("${micronaut.upload.dir:src/uploads/images}") String uploadDir,
                            @Value("${micronaut.base.url:http://localhost:8081/images}") String baseUrl) {
        this.uploadDir = uploadDir;
        this.baseUrl = baseUrl;
        try {
            Files.createDirectories(Paths.get(uploadDir));
            LOG.info("Created upload directory: {}", uploadDir);
        } catch (IOException e) {
            LOG.error("Could not create upload directory: {}", uploadDir, e);
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    public String saveFile(byte[] fileBytes, String originalFileName) throws IOException {
        String fileExtension = originalFileName.contains(".")
                ? originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
                : "jpg";
        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
        Path filePath = Paths.get(uploadDir, newFileName);

        Files.write(filePath, fileBytes);
        LOG.info("Saved file to: {}", filePath);

        return newFileName;

    }

    public String uploadImageQuiz(CompletedFileUpload file) throws IOException {
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IOException("File too large, max 5MB");
        }

        String originalFileName = file.getFilename() != null ? file.getFilename() : "avatar.jpg";
        String uniqueFileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_" + originalFileName;

        Path uploadDirs = Paths.get(uploadDir);
        if (!Files.exists(uploadDirs)) {
            Files.createDirectories(uploadDirs);
        }

        Path filePath = uploadDirs.resolve(uniqueFileName);
        Files.write(filePath, file.getBytes());

        return uniqueFileName;
    }
}