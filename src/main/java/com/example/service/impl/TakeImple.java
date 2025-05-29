package com.example.service.impl;

import com.example.configurations.QuizMapper;
import com.example.configurations.TakeMapper;
import com.example.dtos.TakeDTO;
import com.example.dtos.reponseDTO.ExamAnswerDTO;
import com.example.dtos.reponseDTO.avgTake;
import com.example.dtos.reponseDTO.detailsAnswer;
import com.example.dtos.requestDTO.ResultExamRequest;
import com.example.entities.Account;
import com.example.entities.Quiz;
import com.example.entities.Take;
import com.example.helper.TotalTime;
import com.example.repositories.*;
import com.example.service.TakeService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class TakeImple implements TakeService {

    @Inject
    TakeRepository takeRepository;
    @Inject
    TakeAnswerRepository takeAnswerRepository;
    @Inject
    TakeMapper takeMapper;
    @Inject
    QuizRepository quizRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    QuizMapper quizMapper;


    @Inject
    QuizAnswerRepository quizAnswerRepository;

    @Inject
    private QuizQuestionRepository quizQuestionRepository;

    @Override
    public Take saveTake(TakeDTO takeDTO) {
        try {
            Take take = takeMapper.toEntity(takeDTO);
            take.setFinishedAt(LocalDateTime.now());
            return takeRepository.save(take);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Take: " + e.getMessage(), e);
        }

    }

    @Override
    public Take findTakeById(long idTake) {
        try {
            Optional<Take> takeOptional = takeRepository.findById(idTake);
            if (!takeOptional.isPresent()) {
                throw new RuntimeException("Không tìm thấy take");
            } else {
                return takeOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Take: " + e.getMessage(), e);
        }
    }

    @Override
    public ExamAnswerDTO getDetailTake(Long idTake) {
        try {
            ExamAnswerDTO examAnswerDTO = new ExamAnswerDTO();
            examAnswerDTO.setTakeId(idTake);
            List<detailsAnswer> detailsAnswers = takeAnswerRepository.getContent(idTake);
            for (detailsAnswer detailsAnswer : detailsAnswers) {
                detailsAnswer.setDemoAnswers(quizAnswerRepository.getAnswerByIdQuestion(detailsAnswer.getQuestionId()));
            }
            examAnswerDTO.setDetailsAnswer(detailsAnswers);
            return examAnswerDTO;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi: " + e.getMessage());
        }
    }

    @Override
    public avgTake getAvgTake(Long idUser) {
        try {
            List<Take> takes = takeRepository.getListTakeByUserId(idUser);
            avgTake avgTaker = new avgTake();
            avgTaker.setTotalTake(takes.size());
            List<String> totalTime = new ArrayList<>();
            int totalScore = 0;
            for (Take take : takes) {
                totalScore += take.getScore();

                totalTime.add(take.getTime());
            }
            double avgScore = (double) totalScore / takes.size(); // Điểm trung bình
            avgTaker.setAvgScore(avgScore);
            avgTaker.setAvgtime(TotalTime.sumTimes(totalTime));
            return avgTaker;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi: " + e.getMessage());
        }
    }


    @Override
    public long countTakesByQuizCreator(Long idUser) {
        try {
            return takeRepository.countTakesByQuizCreator(idUser);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi: " + e.getMessage());
        }
    }

    @Override
    public List<ResultExamRequest> getTakeByUserName(String username) {
        try {
            List<Take> takeList =  takeRepository.getTakeByUserName(username);
            List<ResultExamRequest> listResult = new ArrayList<>();


            for (Take take : takeList) {
                Quiz quiz = quizRepository.findById(take.getQuizId())
                        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy quiz với ID: " + take.getQuizId()));
                int numberOfQuestions = quizQuestionRepository.countByQuizId(quiz.getId());
                Optional<Account> optionalAccount = userRepository.findById(quiz.getUserId());
                Account account = optionalAccount.get();

                ResultExamRequest dto = new ResultExamRequest();
                dto.setTitle(quiz.getTitle());
                dto.setNumberquiz(numberOfQuestions);
                dto.setImage(quiz.getImage());
                dto.setUserName(account.getUserName());
                dto.setImageUser(account.getAvatar());
                dto.setQuizId(quiz.getId());
                dto.setCorrect(take.getScore());
                dto.setFinishedAt(take.getFinishedAt());
                dto.setTime(take.getTime());
                dto.setCorrect(take.getCorrect());
                dto.setTakeId(take.getId());

                listResult.add(dto);
            }
            return listResult;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách bài làm của người dùng: " + e.getMessage(), e);
        }
    }

}
