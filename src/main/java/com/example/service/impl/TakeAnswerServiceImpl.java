package com.example.service.impl;

import com.example.configurations.TakeAnswerMapper;
import com.example.configurations.TakeMapper;
import com.example.dtos.TakeanswerDTO;
import com.example.entities.Take;
import com.example.entities.Takeanswer;
import com.example.repositories.TakeAnswerRepository;
import com.example.service.TakeAnsweService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class TakeAnswerServiceImpl implements TakeAnsweService {
    @Inject
    TakeAnswerRepository takeAnswerRepository;

    @Inject
    TakeAnswerMapper takeAnswerMapper;

    @Override
    public List<Takeanswer> saveTakeAnswer(List<TakeanswerDTO> takeanswerDTOS) {
        try {
            if (takeanswerDTOS.isEmpty()) {
                throw new Exception("danh sách đáp án rỗng");
            }
            List<Takeanswer> takeanswers = takeAnswerMapper.toEntityList(takeanswerDTOS);
            return takeAnswerRepository.saveAll(takeanswers);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Takeanswer> findAllByTakeid(long idTake) {
        try {
            List<Takeanswer> takeanswers = takeAnswerRepository.findAllByTakeId(idTake);
            if (!takeanswers.isEmpty()) {
                throw new RuntimeException("Không tìm thấy takeAnswer");
            } else {
                return takeanswers;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Take: " + e.getMessage(), e);
        }
    }
}
