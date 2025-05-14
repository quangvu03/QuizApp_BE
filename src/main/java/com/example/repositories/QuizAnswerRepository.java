package com.example.repositories;

import com.example.dtos.requestDTO.demoAnswer;
import com.example.entities.Quizanswer;
import com.example.entities.Takeanswer;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface QuizAnswerRepository extends CrudRepository<Quizanswer, Long> {

    @Query(nativeQuery = true,
            value = "SELECT id, correct, content FROM quizanswer WHERE quizId = :quizId AND questionId = :questionId")
    List<demoAnswer> findByQuizIdAndQuestionId(Long quizId, Long questionId);

    @Query(value = "SELECT id, correct, content FROM quizanswer WHERE questionId = :questionId", nativeQuery = true)
    List<demoAnswer> getAnswerByIdQuestion(Long questionId);

}
