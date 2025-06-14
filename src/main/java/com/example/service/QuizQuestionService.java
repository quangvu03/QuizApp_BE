package com.example.service;

import com.example.dtos.QuizquestionDTO;
import com.example.dtos.reponseDTO.demoQuiz;
import com.example.dtos.reponseDTO.examResponse;

import java.util.List;

public interface QuizQuestionService {

    List<demoQuiz> demoQuiz(long idQuiz);

    examResponse getExam(Long idQuiz);

    Long saveQuestion(QuizquestionDTO quizquestionDTO);

    boolean deleteQuestion(Long id);

    boolean updateQuestion(QuizquestionDTO quizquestionDTO);
}
