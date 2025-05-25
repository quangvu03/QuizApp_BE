package com.example.dtos.reponseDTO;

import com.example.dtos.requestDTO.demoAnswer;
import io.micronaut.serde.annotation.Serdeable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Serdeable
public class demoQuiz {
    private Long id;
    private String title;
    private String type;
    private Timestamp createdAt;
    private String content;
    private List<demoAnswer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<demoAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<demoAnswer> answers) {
        this.answers = answers;
    }
}
