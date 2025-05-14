package com.example.dtos.reponseDTO;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class ExamAnswerDTO {
    private Long TakeId;

    private List<detailsAnswer> detailsAnswer;

    public Long getTakeId() {
        return TakeId;
    }

    public void setTakeId(Long takeId) {
        TakeId = takeId;
    }

    public List<com.example.dtos.reponseDTO.detailsAnswer> getDetailsAnswer() {
        return detailsAnswer;
    }

    public void setDetailsAnswer(List<com.example.dtos.reponseDTO.detailsAnswer> detailsAnswer) {
        this.detailsAnswer = detailsAnswer;
    }

    @Override
    public String toString() {
        return "ExamAnswerDTO{" +
                "TakeId=" + TakeId +
                ", detailsAnswer=" + detailsAnswer +
                '}';
    }
}
