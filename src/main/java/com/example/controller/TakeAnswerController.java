package com.example.controller;

import com.example.configurations.TakeAnswerMapper;
import com.example.dtos.TakeDTO;
import com.example.dtos.TakeanswerDTO;
import com.example.entities.Takeanswer;
import com.example.service.TakeAnsweService;
import com.example.service.TakeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("api/takeAnswer")
public class TakeAnswerController {

    @Inject
    TakeAnswerMapper takeAnswerMapper;

    @Inject
    TakeAnsweService takeAnsweService;

    @Post("saveListTakeAnswer")
    public HttpResponse<?> saveTakeAnswers(@Body List<TakeanswerDTO> takeanswerDTOList) {
        try {
            return HttpResponse.ok(Map.of("result", takeAnsweService.saveTakeAnswer(takeanswerDTOList)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

//
//    @Get("getTakeById/{idTake}")
//    public HttpResponse<?> findById(@PathVariable("idTake") Long idTake) {
//        try {
//            return HttpResponse.ok(Map.of("result", takeService.findTakeById(idTake)));
//        } catch (Exception e) {
//            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
//        }
//    }

    @Get("findAllByTakeId")
    public HttpResponse<?> findAllByTakeId(@QueryValue int idTake) {
        try {
            return HttpResponse.ok(Map.of("result", takeAnsweService.findAllByTakeid(idTake)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

}