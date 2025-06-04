package com.example.controller;

import com.example.dtos.QuizquestionDTO;
import com.example.dtos.reponseDTO.demoQuiz;
import com.example.dtos.reponseDTO.examResponse;
import com.example.repositories.QuizAnswerRepository;
import com.example.service.QuizQuestionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("api/question")
public class QuestionController {

    @Inject
    QuizQuestionService quizQuestionService;

    @Get("/getDetailQuiz/{id}")
    public HttpResponse<?> getDetailsQuiz(@PathVariable("id") Long idquiz) {
        try {
            List<demoQuiz> detailQuizAnswerList = quizQuestionService.demoQuiz(idquiz);

            if (detailQuizAnswerList.isEmpty()) {
                return HttpResponse.ok(
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

    @Post("/saveQuestion")
    public HttpResponse<?> saveQuestion(@Body QuizquestionDTO quizquestionDTO) {
        try {
            Long result = quizQuestionService.saveQuestion(quizquestionDTO);
            if (result > 0) {
                return HttpResponse.ok(Map.of("idQuestion", result));
            } else {
                return HttpResponse.badRequest(Map.of("result", result));
            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Delete("/deleteQuestion/{id}")
    public HttpResponse<?> deleteQuestion(@PathVariable("id") Long id) {
        try {
            boolean result = quizQuestionService.deleteQuestion(id);
            if (result) {
                return HttpResponse.ok(Map.of("result", "Câu hỏi đã được xóa thành công"));
            } else {
                return HttpResponse.notFound(Map.of("result", "Không tìm thấy câu hỏi với ID: " + id));
            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Put("/updateQuestion")
    public HttpResponse<?> updateQuestion(@Body QuizquestionDTO quizquestionDTO) {
        try {
            boolean result = quizQuestionService.updateQuestion(quizquestionDTO);
            if (result) {
                return HttpResponse.ok(Map.of("result", "Câu hỏi đã được cập nhật thành công"));
            } else {
                return HttpResponse.badRequest(Map.of("result", "Không thể cập nhật câu hỏi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

}
