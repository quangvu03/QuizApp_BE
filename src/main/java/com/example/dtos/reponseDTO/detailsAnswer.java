package com.example.dtos.reponseDTO;

import com.example.dtos.requestDTO.demoAnswer;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class detailsAnswer {
    private Long questionId;
    private String content;
    private Long answerId;

    List<demoAnswer> demoAnswers;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public List<demoAnswer> getDemoAnswers() {
        return demoAnswers;
    }

    public void setDemoAnswers(List<demoAnswer> demoAnswers) {
        this.demoAnswers = demoAnswers;
    }


    @Override
    public String toString() {
        return "detailsAnswer{" +
                "questionId=" + questionId +
                ", content='" + content + '\'' +
                ", answerId=" + answerId +
                ", demoAnswers=" + demoAnswers +
                '}';
    }
}
