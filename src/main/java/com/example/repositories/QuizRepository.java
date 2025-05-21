package com.example.repositories;

import com.example.entities.Quiz;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query(value = "SELECT COUNT(*) FROM quiz WHERE userId = :userId", nativeQuery = true)
    int countByUserId(Long userId);

    @Query("SELECT q FROM Quiz q WHERE LOWER(q.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Quiz> searchByTitleLike(String keyword);

    List<Quiz> findByUserId(Long userId);

}
