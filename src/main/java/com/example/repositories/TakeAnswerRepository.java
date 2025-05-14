package com.example.repositories;

import com.example.dtos.reponseDTO.detailsAnswer;
import com.example.dtos.requestDTO.demoAnswer;
import com.example.entities.Takeanswer;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface TakeAnswerRepository extends CrudRepository<Takeanswer, Long> {

    List<Takeanswer> findAllByTakeId(Long takeId);

    @Query(value = "SELECT ta.questionId, q.content, ta.answerId AS answerId FROM takeanswer ta " +
            "JOIN quizquestion q ON ta.questionId = q.id WHERE ta.takeId = :takeId", nativeQuery = true)
    List<detailsAnswer> getContent(Long takeId);

}
