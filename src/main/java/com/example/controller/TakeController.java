package com.example.controller;

import com.example.dtos.TakeDTO;
import com.example.entities.Take;
import com.example.service.TakeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.Map;

@Controller("api/take")
public class TakeController {

    @Inject
    TakeService takeService;

    @Post("saveTake")
    public HttpResponse<?> saveTake(@Body TakeDTO takeDTO) {
        try {
            return HttpResponse.ok(Map.of("result", takeService.saveTake(takeDTO)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

    @Get("getTakeById/{idTake}")
    public HttpResponse<?> findById(@PathVariable("idTake") Long idTake) {
        try {
            return HttpResponse.ok(Map.of("result", takeService.findTakeById(idTake)));
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of("result", "Lỗi: " + e.getMessage()));
        }
    }

}