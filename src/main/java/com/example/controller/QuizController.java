package com.example.controller;


import com.example.dtos.reponseDTO.detailQuiz;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.dtos.reponseDTO.getListUserQuizDTO;
import com.example.service.QuizService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("api/quiz")
public class QuizController {

    @Inject
    QuizService quizService;

    @Get("/findAll")
    public HttpResponse<?> finallQuiz() {
        try {
            List<getListQuizDTO> getListQuizDTOS = quizService.findAll();
            System.out.println("getListQuizDTOS: "+ getListQuizDTOS);
            if (!getListQuizDTOS.isEmpty()) {
                return HttpResponse.ok(Map.of("result", getListQuizDTOS));
            } else {
                return HttpResponse.badRequest(Map.of("result", "danh sách rỗng"));
            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/findAllbyUser")
    public HttpResponse<?> finallQuizbyUser() {
        try {
            List<getListUserQuizDTO> getListQuizDTOS = quizService.findAllbyUser();
            if (!getListQuizDTOS.isEmpty()) {
                return HttpResponse.ok(Map.of("result", getListQuizDTOS));
            } else {
                return HttpResponse.badRequest(Map.of("result", "danh sách rỗng"));
            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/getdetails/{id}")
    public HttpResponse<?> getDetailsQuiz(@PathVariable long id) {
        try {
            detailQuiz detailQuiz = quizService.getdetailQuiz(id);
            if (detailQuiz != null) {
                return HttpResponse.ok(Map.of("result", detailQuiz));
            } else {
                return HttpResponse.badRequest(Map.of("result", "Không có chi tiết"));
            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }
}
