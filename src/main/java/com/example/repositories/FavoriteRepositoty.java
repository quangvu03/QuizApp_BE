package com.example.repositories;

import com.example.entities.Favorite;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface FavoriteRepositoty extends CrudRepository<Favorite, Long> {

    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.quizId = :quizId")
    long countByQuizId(Long quizId);
}
