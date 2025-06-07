package com.example.controller;

import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.entities.Favorite;
import com.example.service.FavoriteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

@Controller("api/favorite")
public class FavoriteController {

    @Inject
    private FavoriteService favoriteService;

    @Get(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> getFavoritesByUserId(@PathVariable Long userId) {
        try {
            List<getListQuizDTO> favorites = favoriteService.findByUserId(userId);
            return HttpResponse.ok(Map.of("result", favorites));
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", e.getMessage()));
        }
    }

    @Post(value = "/add", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> addFavorite(@QueryValue Long quizId, @QueryValue Long userId) {
        try {
            Favorite favorite = favoriteService.addFavorite(quizId, userId);
            return HttpResponse.ok(Map.of("result", favorite));
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", e.getMessage()));
        }
    }

    @Delete(value = "/delete", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> deleteFavorite(@QueryValue Long userId, @QueryValue Long quizId) {
        try {
            boolean deleted = favoriteService.deleteFavorite(userId, quizId);
            if (deleted) {
                return HttpResponse.ok(Map.of("result", "Favorite deleted successfully"));
            } else {
                return HttpResponse.notFound(Map.of("result", "Favorite not found"));
            }
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", e.getMessage()));
        }
    }

    @Get(value = "/check", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> isQuizInUserFavorites(@QueryValue Long quizId, @QueryValue Long userId) {
        try {
            boolean isFavorite = favoriteService.isQuizInUserFavorites(quizId, userId);
            return HttpResponse.ok(Map.of("result", isFavorite));
        } catch (Exception e) {
            return HttpResponse.serverError(Map.of("error", e.getMessage()));
        }
    }
}
