package com.example.service.impl;

import com.example.dtos.QuizanswerDTO;
import com.example.entities.Quizanswer;
import com.example.repositories.QuizAnswerRepository;
import com.example.service.QuizAnswerService;
import com.example.service.QuizService;
import io.micronaut.data.repository.CrudRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class QuizAnswerImple implements QuizAnswerService {

    @Inject
    QuizAnswerRepository quizAnswerRepository;

    @Override
    public List<Quizanswer> saveAllAnswer(List<QuizanswerDTO> quizanswerDTOS) {
        List<Quizanswer> quizanswers = quizanswerDTOS.stream()
                .map(dto -> {
                    Quizanswer answer = new Quizanswer();
                    answer.setQuizId(dto.getQuizId());
                    answer.setContent(dto.getContent());
                    answer.setCorrect(dto.isCorrect());
                    answer.setQuestionId(dto.getQuestionId());
                    answer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    return answer;
                })
                .collect(Collectors.toList());

        return quizAnswerRepository.saveAll(quizanswers);
    }


}
