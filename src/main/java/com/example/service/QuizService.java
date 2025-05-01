package com.example.service;

import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.dtos.reponseDTO.getListUserQuizDTO;
import com.example.entities.Quiz;

import java.util.List;

public interface QuizService {
    public List<getListQuizDTO> findAll();

    public List<getListUserQuizDTO> findAllbyUser();


}
