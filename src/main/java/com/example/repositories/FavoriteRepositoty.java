package com.example.repositories;

import com.example.entities.Favorite;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepositoty extends CrudRepository<Favorite, Long> {

    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.quizId = :quizId")
    long countByQuizId(Long quizId);

    public List<Favorite> findByUserId(Long userId);

    public Optional<Favorite> findByUserIdAndQuizId(Long userId, Long quizId);
}
