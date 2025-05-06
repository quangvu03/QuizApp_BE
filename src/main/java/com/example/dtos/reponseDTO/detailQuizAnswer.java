package com.example.dtos.reponseDTO;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;

@Getter
@Serdeable
public class detailQuizAnswer {
    private Long questionId;
    private String content;

    public detailQuizAnswer(Long questionId, String content) {
        this.questionId = questionId;
        this.content = content;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
