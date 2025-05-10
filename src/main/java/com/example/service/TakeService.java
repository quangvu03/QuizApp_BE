package com.example.service;

import com.example.dtos.TakeDTO;
import com.example.entities.Take;
import com.example.entities.Takeanswer;
import jakarta.inject.Singleton;

import java.util.List;

public interface TakeService {
    Take saveTake(TakeDTO takeDTO);

    Take findTakeById(long idTake);


    }
