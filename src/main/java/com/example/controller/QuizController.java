package com.example.controller;


import com.example.dtos.QuizDTO;
import com.example.dtos.reponseDTO.detailQuiz;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.dtos.reponseDTO.getListUserQuizDTO;
import com.example.entities.Quiz;
import com.example.service.QuizService;
import com.example.service.SaveImageService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("api/quiz")
public class QuizController {

    @Inject
    QuizService quizService;

    @Inject
    SaveImageService saveImageService;

    @Get("/findAll")
    public HttpResponse<?> finallQuiz() {
        try {
            List<getListQuizDTO> getListQuizDTOS = quizService.findAll();
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

    @Get("/findByName")
    public HttpResponse<?> findQuizByname(@QueryValue String name) {
        try {
            List<getListQuizDTO> listQuizDTOS = quizService.findQuizByName(name);
//            if (listQuizDTOS != null && !listQuizDTOS.isEmpty()) {
            return HttpResponse.ok(Map.of("result", listQuizDTOS));
//            }
//            else {
//                return HttpResponse.ok(Map.of("result", new ArrayList<>(), "message", "Không có dữ liệu"));
//            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/findByUserName")
    public HttpResponse<?> findByUserName(@QueryValue String username) {
        try {
            List<getListUserQuizDTO> listQuizDTOS = quizService.findQuizByUsername(username);
//            if (listQuizDTOS != null && !listQuizDTOS.isEmpty()) {
            return HttpResponse.ok(Map.of("result", listQuizDTOS));
//            } else {
//                return HttpResponse.ok(Map.of("result", "Không có dữ liệu"));
//            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Post(value = "/createQuiz", consumes = MediaType.MULTIPART_FORM_DATA)
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<?> createQuiz(@Body QuizDTO quizDTO, @Nullable @Part("avatar") CompletedFileUpload file) {
        try {
            // Đảm bảo rằng chuỗi là UTF-8
            if (quizDTO.getTitle() != null) {
                quizDTO.setTitle(new String(quizDTO.getTitle().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }
            if (quizDTO.getContent() != null) {
                quizDTO.setContent(new String(quizDTO.getContent().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }
            if (file != null && file.getFilename() != null) {
                String nameImage = saveImageService.uploadImageQuiz(file);
                quizDTO.setImage(nameImage);
            }
            System.out.println("quizDTO: "+quizDTO);
            return HttpResponse.ok(Map.of("result", quizService.createQuiz(quizDTO)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/findByUserId")
    public HttpResponse<?> findByUserName(@QueryValue Long userId) {
        try {
            List<Quiz> quizList = quizService.findByUserId(userId);
//            if (listQuizDTOS != null && !listQuizDTOS.isEmpty()) {
            return HttpResponse.ok(Map.of("result", quizList));
//            } else {
//                return HttpResponse.ok(Map.of("result", "Không có dữ liệu"));
//            }
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/findQuizzesByUsername")
    public HttpResponse<?> findQuizzesByUsername(@QueryValue String username) {
        try {
            List<getListQuizDTO> quizList = quizService.findQuizzesByUsername(username);
            return HttpResponse.ok(Map.of("result", quizList));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Put(value = "/updateQuiz", consumes = MediaType.MULTIPART_FORM_DATA)
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<?> updateQuiz(
            @Part("id") String id, // Chuyển sang String để chấp nhận chuỗi
            @Part("title") String title,
            @Part("content") String content,
            @Part("image") String image,
            @Nullable @Part("avatar") CompletedFileUpload file
    ) {
        try {
            System.out.println("Received: id=" + id + ", title=" + title + ", content=" + content + ", file=" + (file != null ? file.getFilename() : "null"));

            // Chuyển id thành Long
            Long quizId;
            try {
                quizId = Long.parseLong(id);
            } catch (NumberFormatException e) {
                return HttpResponse.badRequest(Map.of("result", "ID không hợp lệ: " + id));
            }

            QuizDTO quizDTO = new QuizDTO();
            quizDTO.setId(quizId);
            quizDTO.setTitle(title);
            quizDTO.setContent(content);

            if (file != null && file.getFilename() != null) {
                String nameImage = saveImageService.uploadImageQuiz(file);
                quizDTO.setImage(nameImage);
            }else{
                quizDTO.setImage(image);
            }

            return HttpResponse.ok(Map.of("result", quizService.updateQuiz(quizDTO)));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }
}


