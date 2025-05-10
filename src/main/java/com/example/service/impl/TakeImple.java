package com.example.service.impl;

import com.example.configurations.TakeMapper;
import com.example.dtos.TakeDTO;
import com.example.entities.Take;
import com.example.entities.Takeanswer;
import com.example.repositories.TakeRepository;
import com.example.service.TakeService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
public class TakeImple implements TakeService {

    @Inject
    TakeRepository takeRepository;

    @Inject
    TakeMapper takeMapper;

    @Override
    public Take saveTake(TakeDTO takeDTO) {
        try {
            Take take = takeMapper.toEntity(takeDTO);
            take.setFinishedAt(LocalDateTime.now());
            return takeRepository.save(take);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Take: " + e.getMessage(), e);
        }

    }

    @Override
    public Take findTakeById(long idTake) {
        try {
            Optional<Take> takeOptional = takeRepository.findById(idTake);
            if (!takeOptional.isPresent()) {
                throw new RuntimeException("Không tìm thấy take");
            } else {
                return takeOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Take: " + e.getMessage(), e);
        }
    }



}
