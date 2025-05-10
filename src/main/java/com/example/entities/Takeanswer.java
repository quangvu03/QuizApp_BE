package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Serdeable
@Entity
@Table(name = "takeanswer")
public class Takeanswer {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Hibernate sẽ tự tạo ID
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "takeId")
    private Long takeId;

    @Column(name = "questionId")
    private Long questionId;

    @Column(name = "answerId")
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
