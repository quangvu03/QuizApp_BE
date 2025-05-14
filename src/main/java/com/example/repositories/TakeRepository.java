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


}
