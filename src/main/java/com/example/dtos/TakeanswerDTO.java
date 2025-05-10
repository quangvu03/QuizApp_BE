package com.example.dtos;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class TakeanswerDTO {
    private Long id;
    private Long takeId;
    private Long questionId;
    private Long answerId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTakeId() {
        return this.takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
