package com.example.repositories;

import com.example.entities.Take;
import com.example.entities.Takeanswer;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface TakeRepository extends CrudRepository<Take, Long> {
    @Query(value = "SELECT * FROM take WHERE userId = :userId", nativeQuery = true)
    List<Take> getListTakeByUserId(Long userId);

    @Query(value = """
        SELECT COUNT(*) AS total_quizzes_taken FROM take
        WHERE quizId IN ( SELECT id FROM quiz WHERE userId = :userId) """, nativeQuery = true)
    long countTakesByQuizCreator( Long userId);

    @Query(value = """
        SELECT t.* FROM take t
        JOIN account a ON t.userId = a.id
        WHERE a.userName = :username
        """, nativeQuery = true)
    List<Take> getTakeByUserName(String username);

}
