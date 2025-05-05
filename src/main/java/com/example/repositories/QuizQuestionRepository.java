package com.example.repositories;


import com.example.entities.Quizquestion;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface QuizQuestionRepository extends CrudRepository<Quizquestion, Long> {

    @Query(value = "SELECT COUNT(*) FROM quizquestion WHERE quizId = :quizId", nativeQuery = true)
    int countByQuizId(Long quizId);

}
