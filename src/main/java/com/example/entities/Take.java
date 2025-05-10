package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Serdeable
@Entity
@Table(name = "take")
public class Take {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "quizId")
    private Long quizId;

    @Column(name = "status")
    private Byte status;

    @Column(name = "score")
    private Integer score;

    @Column(name = "time")
    private String time;

    @Column(name = "correct")
    private Integer correct;

    @Column(name = "finishedAt")
    private LocalDateTime finishedAt;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return this.quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCorrect() {
        return this.correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public LocalDateTime getFinishedAt() {
        return this.finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
}
