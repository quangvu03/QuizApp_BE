package com.example.service;

import com.example.dtos.QuizanswerDTO;
import com.example.entities.Quizanswer;

import java.util.List;

public interface QuizAnswerService {
    List<Quizanswer> saveAllAnswer(List<QuizanswerDTO> quizanswerDTOS);

    boolean deleteListAnswer(List<Long> answerIds);

    List<Quizanswer> updateAnswer(List<QuizanswerDTO> quizanswerDTOS, List<Long> answerIds);
}
