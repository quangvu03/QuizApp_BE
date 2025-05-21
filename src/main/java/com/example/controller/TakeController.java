package com.example.controller;

import com.example.dtos.TakeDTO;
import com.example.service.TakeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.Map;

@Controller("api/take")
public class TakeController {

    @Inject
    TakeService takeService;

    @Post("/saveTake")
    public HttpResponse<?> saveTake(@Body TakeDTO takeDTO) {
        try {
            return HttpResponse.ok(Map.of("result", takeService.saveTake(takeDTO)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/getTakeById/{idTake}")
    public HttpResponse<?> findById(@PathVariable("idTake") Long idTake) {
        try {
            return HttpResponse.ok(Map.of("result", takeService.findTakeById(idTake)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/getDetailsTake")
    public HttpResponse<?> getDetailsTake(@QueryValue("idTake") Long idTake) {
        try {
            return HttpResponse.ok(Map.of("result", takeService.getDetailTake(idTake)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/getAvgTake")
    public HttpResponse<?> getavgTake(@QueryValue("idUser") Long idUser) {
        try {
            return HttpResponse.ok(Map.of("result", takeService.getAvgTake(idUser)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("/countTakesByQuizCreator")
    public HttpResponse<?> countTakesByQuizCreator(@QueryValue("idUser") Long idUser) {
        try {
            return HttpResponse.ok(Map.of("result", takeService.countTakesByQuizCreator(idUser)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }
}