package com.example.repositories;


import com.example.entities.Quizquestion;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends CrudRepository<Quizquestion, Long> {

    @Query(value = "SELECT COUNT(*) FROM quizquestion WHERE quizId = :quizId", nativeQuery = true)
    int countByQuizId(Long quizId);

    @Query(value = "SELECT id, quizId, title, type, createdAt, content FROM quizquestion WHERE quizId = :quizId", nativeQuery = true)
    List<Quizquestion> findByQuizId(Long quizId);

    @Query(value = "UPDATE Quizquestion q SET q.title = :title, q.content = :content, q.type = :type WHERE q.id = :id", nativeQuery = true)
    void updateQuestionById(Long id, String title, String content, String type);

}
