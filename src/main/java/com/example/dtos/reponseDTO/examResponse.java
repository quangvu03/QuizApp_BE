package com.example.dtos.reponseDTO;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class examResponse {

        private int numberexamQuizDTO;
        private List<examQuizDTO> examQuizDTO;

    public int getNumberexamQuizDTO() {
        return numberexamQuizDTO;
    }

    public void setNumberexamQuizDTO(int numberexamQuizDTO) {
        this.numberexamQuizDTO = numberexamQuizDTO;
    }

    public List<com.example.dtos.reponseDTO.examQuizDTO> getExamQuizDTO() {
        return examQuizDTO;
    }

    public void setExamQuizDTO(List<com.example.dtos.reponseDTO.examQuizDTO> examQuizDTO) {
        this.examQuizDTO = examQuizDTO;
    }

    @Override
    public String toString() {
        return "examResponse{" +
                "numberexamQuizDTO=" + numberexamQuizDTO +
                ", examQuizDTO=" + examQuizDTO +
                '}';
    }
}


