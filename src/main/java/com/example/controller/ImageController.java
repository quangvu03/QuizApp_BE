package com.example.controller;
import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import com.example.repositories.UserRepository;
import com.example.service.SaveImageService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;




    @Controller("/api/upload")
    public class ImageController {

        private final UserRepository userRepository;
        private final SaveImageService fileStorageService;

        @Inject
        public ImageController(UserRepository userRepository, SaveImageService fileStorageService) {
            this.userRepository = userRepository;
            this.fileStorageService = fileStorageService;
        }

        @Post(value = "/uploadAvatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA)
        @ExecuteOn(TaskExecutors.IO)
        public HttpResponse<Map<String, Object>> uploadAvatar(
                @Part("avatar") CompletedFileUpload file,
                @PathVariable("userId") Long userId) {
            try {
                Optional<Account> accountOptional = userRepository.findById(userId);
                if (accountOptional.isEmpty()) {
                    Map<String, Object> error = new HashMap<>();
                    error.put("error", "User not found");
                    return HttpResponse.badRequest(error);
                }
                Account user = accountOptional.get();

                if (file.getSize() > 5 * 1024 * 1024) {
                    Map<String, Object> error = new HashMap<>();
                    error.put("error", "File too large, max 5MB");
                    return HttpResponse.badRequest(error);
                }

                // Lưu file ảnh
                byte[] fileBytes = file.getBytes();
                String fileName = file.getFilename() != null ? file.getFilename() : "avatar.jpg";
                String avatarUrl = fileStorageService.saveFile(fileBytes, fileName);

                // Cập nhật avatar trong database
                user.setAvatar(avatarUrl);
                userRepository.update(user);

                // Trả về URL của ảnh
                Map<String, Object> response = new HashMap<>();
                response.put("avatarUrl", avatarUrl);
                return HttpResponse.ok(response);

            } catch (IOException e) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Failed to upload avatar: " + e.getMessage());
                return HttpResponse.serverError(error);
            }
        }
    }

