package com.example.controller;


import com.example.dtos.AccountDTO;
import com.example.service.AcountService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Map;

@Controller("api/account")
public class UserController {

    @Inject
    AcountService acountService;

    @Post(value = "/create", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> createAccount(@Body AccountDTO userDTO) {
        try {
            return HttpResponse.ok(
                    Map.of("result", acountService.create(userDTO))
            );
        } catch (Exception e) {
            return HttpResponse.badRequest(Map.of(
                    "result", false,
                    "error", "Tạo tài khoản thất bại: " + e.getMessage()));
        }
    }
}
