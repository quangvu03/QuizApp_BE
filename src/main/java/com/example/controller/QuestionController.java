package com.example.controller;

import com.example.dtos.reponseDTO.demoQuiz;
import com.example.dtos.reponseDTO.examResponse;
import com.example.repositories.QuizAnswerRepository;
import com.example.service.QuizQuestionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("api/question")
public class QuestionController {

    @Inject
    QuizQuestionService quizQuestionService;

    @Get("/getDeilQuiz/{id}")
    public HttpResponse<?> getDetailsQuiz(@PathVariable("id") Long idquiz) {
        try {
            List<demoQuiz> detailQuizAnswerList = quizQuestionService.demoQuiz(idquiz);

            if (detailQuizAnswerList.isEmpty()) {
                return HttpResponse.badRequest(
                        Map.of("result", "Không có câu hỏi nào")
                );
            }
            List<demoQuiz> limitedList = detailQuizAnswerList.size() > 5
                    ? detailQuizAnswerList.subList(0, 5)
                    : detailQuizAnswerList;

            return HttpResponse.ok(
                    Map.of("result", limitedList)
            );
        } catch (Exception e) {
            return HttpResponse.badRequest(
                    Map.of("result", "Lỗi: " + e.getMessage())
            );
        }
    }


    @Get("/getExam/{id}")
    public HttpResponse<?> getExam(@PathVariable("id") Long idquiz) {
        try {
            examResponse examResponse = quizQuestionService.getExam(idquiz);

            if (examResponse == null) {
                return HttpResponse.ok(Map.of("result", "không có dữ liệu"));
            } else {
                return HttpResponse.ok(examResponse);
            }
        } catch (Exception e) {
            return HttpResponse.badRequest(
                    Map.of("result", "Lỗi: " + e.getMessage())
            );
        }
    }
}

