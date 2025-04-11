package com.example.controller;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
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
            return HttpResponse.ok(Map.of("result", account));
        } else {
            return HttpResponse.badRequest(Map.of("result", "not found"));
        }
    }

    @Get(value = "/findByUsername", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> findByUsername(@QueryValue("username") String username) {
        AccountDTO account = accountService.findbyUsername(username);
        if (account != null) {
            return HttpResponse.ok(Map.of("result", account));
        } else {
            return HttpResponse.badRequest(Map.of("result", "not found"));
        }
    }

    @Post(value = "/login", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> login(@QueryValue("username") String username,@QueryValue("password") String password) {
        AccountDTO account = accountService.loginbyEmail(username,password);
        if (account != null) {
            return HttpResponse.ok(account);
        } else {
            return HttpResponse.ok(Map.of("result", "not found"));
        }
    }

    @Post(value = "/changePassword", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> changePassword(@QueryValue("email") String email,
                                          @QueryValue("Changepassword") String changePassword) {
        String result = accountService.changPassword(email, changePassword);
        try {
            if (result.equals("successfully")) {
                return HttpResponse.ok(Map.of("result", result));
            } else {
                return HttpResponse.badRequest(Map.of("result", result));
            }
        }catch (Exception e){
            return HttpResponse.serverError("Error: " + e.getMessage());
        }
    }

    @Put("/updateAccount/{id}")
    public HttpResponse<?> updateAccount(@PathVariable Long id, @Body AccountDTO updateDTO) {
        if (updateDTO.getPhone() == null || updateDTO.getPhone().trim().isEmpty()) {
            return HttpResponse.badRequest(Map.of("result", "Phone is null or empty"));
        }

        if (updateDTO.getFullName() == null || updateDTO.getFullName().trim().isEmpty()) {
            return HttpResponse.badRequest(Map.of("result", "FullName is null or empty"));
        }
        Account updatedAccount = accountService.updateAccount(id, updateDTO);
        if(updatedAccount!=null){
            return HttpResponse.ok(Map.of("result", updatedAccount));
        }else{
            return HttpResponse.badRequest(Map.of( "result","Account null"));
        }
    }
}