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
        if (quizanswerDTOS == null || quizanswerDTOS.isEmpty()) {
            return new ArrayList<>(); // Return empty list instead of throwing exception
        }

        try {
            List<Quizanswer> quizanswers = quizanswerDTOS.stream()
                    .filter(dto -> dto != null) // Filter out null DTOs
                    .map(dto -> {
                        Quizanswer answer = new Quizanswer();

                        // Handle null values gracefully
                        if (dto.getQuizId() != null) {
                            answer.setQuizId(dto.getQuizId());
                        }

                        if (dto.getContent() != null) {
                            answer.setContent(dto.getContent());
                        } else {
                            answer.setContent(""); // Default empty content
                        }

                        answer.setCorrect(dto.isCorrect());

                        if (dto.getQuestionId() != null) {
                            answer.setQuestionId(dto.getQuestionId());
                        }

                        answer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                        return answer;
                    })
                    .collect(Collectors.toList());

            return quizAnswerRepository.saveAll(quizanswers);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu danh sách đáp án: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteListAnswer(List<Long> answerIds) {
        if (answerIds == null || answerIds.isEmpty()) {
            throw new IllegalArgumentException("Lỗi: Danh sách ID đáp án không được null hoặc rỗng");
        }

        try {
            for (Long id : answerIds) {
                if (id == null) {
                    throw new IllegalArgumentException("Lỗi: ID đáp án không được null");
                }

                if (quizAnswerRepository.existsById(id)) {
                    quizAnswerRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Lỗi: Không tìm thấy đáp án với ID: " + id);
                }
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa danh sách đáp án: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Quizanswer> updateAnswer(List<QuizanswerDTO> quizanswerDTOS, List<Long> answerIds) {
        if (quizanswerDTOS == null || quizanswerDTOS.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            List<Quizanswer> newAnswers = saveAllAnswer(quizanswerDTOS);

            if (answerIds != null && !answerIds.isEmpty()) {
                deleteListAnswer(answerIds);
            }
            return newAnswers;
        } catch (Exception e) {
            e.printStackTrace();
            // Wrap exceptions
            throw new RuntimeException("Lỗi khi cập nhật đáp án: " + e.getMessage(), e);
        }
    }
}
