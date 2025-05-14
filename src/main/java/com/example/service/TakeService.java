package com.example.service;

import com.example.dtos.TakeDTO;
import com.example.dtos.reponseDTO.ExamAnswerDTO;
import com.example.dtos.reponseDTO.avgTake;
import com.example.entities.Take;

import java.util.List;

public interface TakeService {
    Take saveTake(TakeDTO takeDTO);

    Take findTakeById(long idTake);

    ExamAnswerDTO getDetailTake(Long idTake);

    avgTake getAvgTake(Long idUser);


    }
