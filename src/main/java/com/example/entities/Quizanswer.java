package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;
@Serdeable
@Entity
@Table(name = "quizanswer", catalog = "")
public class Quizanswer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "quizId", nullable = true)
    private Long quizId;
    
    @Column(name = "questionId", nullable = true)
    private Long questionId;
    
    @Column(name = "correct", nullable = true)
    private Byte correct;
    
    @Column(name = "content", nullable = true, length = -1)
    private String content;
    
    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;

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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Byte getCorrect() {
        return correct;
    }

    public void setCorrect(Byte correct) {
        this.correct = correct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quizanswer that = (Quizanswer) o;
        return id == that.id && Objects.equals(quizId, that.quizId) && Objects.equals(questionId, that.questionId) && Objects.equals(correct, that.correct) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, questionId, correct, content, createdAt);
    }
}
