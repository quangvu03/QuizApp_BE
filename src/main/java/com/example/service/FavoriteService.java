package com.example.service;

import com.example.dtos.QuizDTO;
import com.example.dtos.reponseDTO.detailQuiz;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.entities.Favorite;

import java.util.List;

public interface FavoriteService {

    List<getListQuizDTO> findByUserId(long userId);

    Favorite addFavorite(Long quizId, Long userId);

    boolean deleteFavorite(Long userId, Long quizId);

    boolean isQuizInUserFavorites(Long quizId, Long userId);

}
