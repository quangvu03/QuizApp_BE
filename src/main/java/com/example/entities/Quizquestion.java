package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;
@Serdeable

@Entity
@Table(name = "quizquestion")
public class Quizquestion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "quizId", nullable = true)
    private Long quizId;
    
    @Column(name = "title", nullable = true, length = 255)
    private String title;
    
    @Column(name = "type", nullable = true)
    private String type;
    
    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;
    
    @Column(name = "content", nullable = true, length = -1)
    private String content;

    @Column(name = "explanation", nullable = true, length = -1)
    private String explanation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
