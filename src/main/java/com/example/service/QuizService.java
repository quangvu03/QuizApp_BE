package com.example.service;

import com.example.dtos.QuizDTO;
import com.example.dtos.reponseDTO.detailQuiz;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.dtos.reponseDTO.getListUserQuizDTO;
import com.example.entities.Quiz;

import java.util.List;

public interface QuizService {
    List<getListQuizDTO> findAll();

    List<getListUserQuizDTO> findAllbyUser();

    detailQuiz getdetailQuiz(long idQuiz);

    List<getListQuizDTO> findQuizByName(String name);

    List<getListUserQuizDTO> findQuizByUsername(String username);

    QuizDTO createQuiz(QuizDTO quizDTO);

    List<Quiz> findByUserId(Long idUser);

    List<getListQuizDTO> findQuizzesByUsername(String username);

    QuizDTO updateQuiz(QuizDTO quizDTO);

    void deleteQuiz(Long idQuiz);

}
