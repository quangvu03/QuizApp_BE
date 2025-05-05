package com.example.service.impl;

import com.example.configurations.QuizMapper;
import com.example.dtos.reponseDTO.detailQuiz;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.dtos.reponseDTO.getListUserQuizDTO;
import com.example.entities.Account;
import com.example.entities.Quiz;
import com.example.repositories.FavoriteRepositoty;
import com.example.repositories.QuizQuestionRepository;
import com.example.repositories.QuizRepository;
import com.example.repositories.UserRepository;
import com.example.service.QuizService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class QuizserviceImple implements QuizService {

    @Inject
    QuizRepository quizRepository;

    @Inject
    QuizQuestionRepository quizQuestion;


    @Inject
    UserRepository userRepository;

    @Inject
    QuizMapper quizMapper;

    @Inject
    FavoriteRepositoty favoriteRepositoty;


    @Override
    public List<getListQuizDTO> findAll() {
        List<Quiz> quizList = quizRepository.findAll();
        List<getListQuizDTO> resultList = new ArrayList<getListQuizDTO>();
        for (Quiz quiz : quizList) {
            int numberOfQuestions = quizQuestion.countByQuizId(quiz.getId());
            Optional<Account> username = userRepository.findById(quiz.getUserId());
            Account account = username.get();
            getListQuizDTO dto = quizMapper.toGetListQuizDTO(quiz);
            dto.setId(quiz.getId());
            dto.setNumberquiz(numberOfQuestions);
            dto.setUserName(account.getUserName());
            dto.setTitle(quiz.getTitle());
            dto.setImage(quiz.getImage());
            dto.setImageUser(account.getAvatar());
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public List<getListUserQuizDTO> findAllbyUser() {
        List<Account> user = userRepository.findAll();
        List<getListUserQuizDTO> resultList = new ArrayList<>();
        for (Account account : user) {
            getListUserQuizDTO dto = new getListUserQuizDTO();
            dto.setUsername(account.getUserName());
            dto.setNumberquiz(quizRepository.countByUserId(account.getId()));
            dto.setImage(account.getAvatar());
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public detailQuiz getdetailQuiz(long idQuiz) {
        Quiz quiz = quizRepository.findById(idQuiz).orElse(null);
        long id = quiz.getId();
        Account account = userRepository.findById(id).orElse(null);
        int numberquestion = quizQuestion.countByQuizId(id);
        long numberfavorite = favoriteRepositoty.countByQuizId(id);
        return new detailQuiz(id, quiz.getTitle(), quiz.getContent(), quiz.getCreatedAt(), quiz.getImage(), account.getUserName(), numberquestion, account.getAvatar(), numberfavorite);
    }
}

