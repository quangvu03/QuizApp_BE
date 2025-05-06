package com.example.controller;

import com.example.dtos.reponseDTO.demoQuiz;
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
    public HttpResponse<?> getDetailsQuiz(@PathVariable("id") Long idquiz){

        List<demoQuiz> detailQuizAnswerList = quizQuestionService.demoQuiz(idquiz);
        try {
            if(detailQuizAnswerList.isEmpty()){
                return HttpResponse.badRequest(
                        Map.of("result", "Không có câu hỏi nào")
                );
            }else{
                return HttpResponse.ok(
                        Map.of("result", detailQuizAnswerList)
                );
            }

        }catch (Exception e){
            return HttpResponse.badRequest(
                    Map.of("result", "Lỗi: "+e.getMessage())
            );
        }
    }
}
