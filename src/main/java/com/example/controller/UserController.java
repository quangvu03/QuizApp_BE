package com.example.controller;


import com.example.dtos.AccountDTO;
import com.example.service.AcountService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.Map;

@Controller("api/account")
public class UserController {

    @Inject
    AcountService accountService;

    @Post(value = "/create", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> createAccount(@Body AccountDTO userDTO) {
        try {
            return HttpResponse.ok(
                    Map.of("result", accountService.create(userDTO))
            );
        } catch (Exception e) {
            return HttpResponse.badRequest(
                    Map.of(
                    "result", false));
        }
    }

    @Get(value = "/findByEmail", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<AccountDTO> findbyEmail(@QueryValue("email") String email){
        try {
            AccountDTO account = accountService.findbyEmail(email);
            return HttpResponse.ok(account);
        }catch (Exception e){
            return HttpResponse.serverError();
        }
    }

    @Get(value = "/findByUsername", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<AccountDTO> findByUsername(@QueryValue("username") String username){
        try {
            AccountDTO account = accountService.findbyUsername(username);
            return HttpResponse.ok(account);
        }catch (Exception e){
            return HttpResponse.serverError();
        }
    }
}
