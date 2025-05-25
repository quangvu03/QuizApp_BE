package com.example.service;

import com.example.dtos.reponseDTO.detailQuiz;
import com.example.entities.Favorite;

import java.util.List;

public interface FavoriteService {

    List<Favorite> findByUserId(long userId);

}
