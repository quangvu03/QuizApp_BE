package com.example.controller;

import com.example.dtos.AccountDTO;
import com.example.service.AccountService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.Map;

@Controller("api/account")
public class UserController {

    @Inject
    AccountService accountService;

    @Post(value = "/create", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> createAccount(@Body AccountDTO userDTO) {
        try {
            return HttpResponse.ok(
                    Map.of("result", accountService.create(userDTO))
            );
        } catch (Exception e) {
            return HttpResponse.badRequest(
                    Map.of("result", false)
            );
        }
    }

    @Get(value = "/findByEmail", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> findbyEmail(@QueryValue("email") String email) {
        AccountDTO account = accountService.findbyEmail(email);
        if (account != null) {
            return HttpResponse.ok(account);
        } else {
            return HttpResponse.ok(Map.of("result", "not found"));
        }
    }

    @Get(value = "/findByUsername", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> findByUsername(@QueryValue("username") String username) {
        AccountDTO account = accountService.findbyUsername(username);
        if (account != null) {
            return HttpResponse.ok(account);
        } else {
            return HttpResponse.ok(Map.of("result", "not found"));
        }
    }
}