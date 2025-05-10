package com.example.repositories;

import com.example.entities.Take;
import com.example.entities.Takeanswer;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface TakeRepository extends CrudRepository<Take, Long> {



}
