package com.example.controller;

import com.example.dtos.QuizanswerDTO;
import com.example.entities.Quizanswer;
import com.example.service.QuizAnswerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("api/answer")
public class QuizAnswerController {

    @Inject
    QuizAnswerService quizAnswerService;

    @Post("/saveAnswer")
    public HttpResponse<?> saveListAnswer( @Body  List<QuizanswerDTO> quizanswerDTOS){
        if(quizanswerDTOS.size()<0){
            throw new IllegalArgumentException("Lỗi: danh sách đáp án rỗng");
        }
        try {
            List<Quizanswer> quizanswers = quizAnswerService.saveAllAnswer(quizanswerDTOS);
            if(!quizanswers.isEmpty()){
                return HttpResponse.ok(Map.of("result", quizanswers));
            }else{
                return HttpResponse.badRequest(Map.of("result", "Không lưu được kết quả!"));
            }
        }catch (Exception e){
            return HttpResponse.badRequest("Lỗi: "+e.getMessage());
        }
    }
}
