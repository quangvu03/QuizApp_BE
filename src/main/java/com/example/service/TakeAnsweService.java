package com.example.service;

import com.example.dtos.TakeanswerDTO;
import com.example.entities.Takeanswer;

import java.util.List;

public interface TakeAnsweService {
    List<Takeanswer> saveTakeAnswer(List<TakeanswerDTO> takeanswerDTOS);

    List<Takeanswer> findAllByTakeid(long idTake);

}
