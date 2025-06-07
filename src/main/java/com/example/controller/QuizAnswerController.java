package com.example.controller;

import com.example.dtos.QuizanswerDTO;
import com.example.entities.Quizanswer;
import com.example.service.QuizAnswerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller("api/answer")
public class QuizAnswerController {

    @Inject
    QuizAnswerService quizAnswerService;

    @Inject
    ObjectMapper objectMapper;

    @Post("/saveAnswer")
    public HttpResponse<?> saveListAnswer(@Body List<QuizanswerDTO> quizanswerDTOS) {
        try {
            List<Quizanswer> quizanswers = quizAnswerService.saveAllAnswer(quizanswerDTOS);
            return HttpResponse.ok(Map.of("result", quizanswers));
        } catch (Exception e) {
            return HttpResponse.badRequest("Lỗi: " + e.getMessage());
        }
    }

    @Put("/updateAnswers")
    public HttpResponse<?> updateAnswer(@Body Map<String, Object> requestBody) {
        try {
            System.out.println("Received request body: " + requestBody);
            // Kiểm tra request body
            if (requestBody == null || !requestBody.containsKey("newAnswers") || !requestBody.containsKey("oldAnswerIds")) {
                return HttpResponse.badRequest("Thiếu trường: newAnswers hoặc oldAnswerIds");
            }

            // Lấy danh sách newAnswers
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rawAnswers = (List<Map<String, Object>>) requestBody.get("newAnswers");
            if (rawAnswers == null || rawAnswers.isEmpty()) {
                return HttpResponse.badRequest("newAnswers không được null hoặc rỗng");
            }

            List<QuizanswerDTO> quizanswerDTOS = rawAnswers.stream()
                    .map(item -> objectMapper.convertValue(item, QuizanswerDTO.class))
                    .collect(Collectors.toList());

            @SuppressWarnings("unchecked")
            List<Number> rawIds = (List<Number>) requestBody.get("oldAnswerIds");
            if (rawIds == null || rawIds.isEmpty()) {
                return HttpResponse.badRequest("oldAnswerIds không được null hoặc rỗng");
            }
            List<Long> answerIds = rawIds.stream()
                    .map(Number::longValue)
                    .collect(Collectors.toList());

            // Gọi service để cập nhật
            List<Quizanswer> updatedAnswers = quizAnswerService.updateAnswer(quizanswerDTOS, answerIds);
            return HttpResponse.ok(Map.of("result", updatedAnswers));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.badRequest("Lỗi: " + e.getMessage());
        }
    }
}