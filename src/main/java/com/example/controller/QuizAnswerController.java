package com.example.controller;

import com.example.dtos.QuizanswerDTO;
import com.example.entities.Quizanswer;
import com.example.service.QuizAnswerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("api/answer")
public class QuizAnswerController {

    @Inject
    QuizAnswerService quizAnswerService;

    @Post("/saveAnswer")
    public HttpResponse<?> saveListAnswer(@Body List<QuizanswerDTO> quizanswerDTOS) {
        try {
            // Process even if list is null or empty
            List<Quizanswer> quizanswers = quizAnswerService.saveAllAnswer(quizanswerDTOS);

            // Return success response even if the result is empty
            return HttpResponse.ok(Map.of("result", quizanswers));
        } catch (Exception e) {
            return HttpResponse.badRequest("Lỗi: " + e.getMessage());
        }
    }


    @Put("/updateAnswers")
    public HttpResponse<?> updateAnswer(
            @Body Map<String, Object> requestBody) {
        try {
            @SuppressWarnings("unchecked")
            List<QuizanswerDTO> quizanswerDTOS = (List<QuizanswerDTO>) requestBody.get("newAnswers");
            @SuppressWarnings("unchecked")
            List<Long> answerIds = (List<Long>) requestBody.get("oldAnswerIds");
            List<Quizanswer> updatedAnswers = quizAnswerService.updateAnswer(quizanswerDTOS, answerIds);
            return HttpResponse.ok(Map.of("result", updatedAnswers));
        } catch (Exception e) {
            return HttpResponse.badRequest("Lỗi: " + e.getMessage());
        }
    }
}
