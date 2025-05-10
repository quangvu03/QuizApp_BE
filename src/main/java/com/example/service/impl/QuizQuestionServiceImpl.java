package com.example.service.impl;

import com.example.dtos.reponseDTO.demoQuiz;
import com.example.dtos.reponseDTO.examQuizDTO;
import com.example.dtos.reponseDTO.examResponse;
import com.example.dtos.requestDTO.demoAnswer;
import com.example.entities.Quizquestion;
import com.example.repositories.QuizAnswerRepository;
import com.example.repositories.QuizQuestionRepository;
import com.example.repositories.QuizRepository;
import com.example.service.QuizQuestionService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class QuizQuestionServiceImpl implements QuizQuestionService {

    @Inject
    QuizRepository quizRepository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;

    @Inject
    QuizAnswerRepository quizAnswerRepository;



    @Override
    public List<demoQuiz> demoQuiz(long idQuiz) {
        List<Quizquestion> quizquestions = quizQuestionRepository.findByQuizId(idQuiz);
        List<demoQuiz> demoQuizs = new ArrayList<>();
        List<demoAnswer> demoAnswer;


        for (Quizquestion quizquestion : quizquestions) {
            demoQuiz demoQuiz = new demoQuiz();

            demoQuiz.setId(quizquestion.getQuizId());
            demoQuiz.setTitle(quizquestion.getTitle());
            demoQuiz.setType(quizquestion.getType());
            demoQuiz.setLevel(quizquestion.getLevel());
            demoQuiz.setCreatedAt(quizquestion.getCreatedAt());
            demoQuiz.setContent(quizquestion.getContent());
            demoAnswer = quizAnswerRepository.findByQuizIdAndQuestionId(idQuiz, quizquestion.getId());
            demoQuiz.setAnswers(demoAnswer);
            demoQuizs.add(demoQuiz);

        }
        return demoQuizs;
    }

    @Override
    public examResponse getExam(Long idQuiz) {
        List<Quizquestion> quizquestions = quizQuestionRepository.findByQuizId(idQuiz);
        List<examQuizDTO> examQuizDTOs = new ArrayList<>();
        examResponse examResponse = new examResponse();

        for (Quizquestion quizquestion : quizquestions) {
            examQuizDTO examQuizDTO = new examQuizDTO();

            examQuizDTO.setId(quizquestion.getId());
            examQuizDTO.setTitle(quizquestion.getTitle());
            examQuizDTO.setType(quizquestion.getType());
            examQuizDTO.setLevel(quizquestion.getLevel());
            examQuizDTO.setContent(quizquestion.getContent());

            List<demoAnswer> answers = quizAnswerRepository.findByQuizIdAndQuestionId(idQuiz, quizquestion.getId());
            examQuizDTO.setAnswers(answers);

            examQuizDTOs.add(examQuizDTO);
        }

        examResponse.setExamQuizDTO(examQuizDTOs);
        examResponse.setNumberexamQuizDTO(quizQuestionRepository.countByQuizId(idQuiz));
        return examResponse;
    }

}
