package com.example.service;

import com.example.dtos.reponseDTO.demoQuiz;

import java.util.List;

public interface QuizQuestionService {

    List<demoQuiz> demoQuiz(long idQuiz);
}
