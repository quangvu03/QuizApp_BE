package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;
@Serdeable

@Entity
@Table(name = "take", catalog = "")
public class Take {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "userId", nullable = true)
    private Long userId;
    
    @Column(name = "quizId", nullable = true)
    private Long quizId;
    
    @Column(name = "status", nullable = true)
    private Byte status;
    
    @Column(name = "score", nullable = true)
    private Integer score;
    
    @Column(name = "startedAt", nullable = true)
    private Timestamp startedAt;
    
    @Column(name = "finishedAt", nullable = true)
    private Timestamp finishedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Timestamp getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Timestamp finishedAt) {
        this.finishedAt = finishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Take that = (Take) o;
        return id == that.id && Objects.equals(userId, that.userId) && Objects.equals(quizId, that.quizId) && Objects.equals(status, that.status) && Objects.equals(score, that.score) && Objects.equals(startedAt, that.startedAt) && Objects.equals(finishedAt, that.finishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, quizId, status, score, startedAt, finishedAt);
    }
}
