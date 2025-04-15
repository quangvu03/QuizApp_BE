package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Serdeable
@Getter
@Setter
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
    private boolean correct;

    @Column(name = "content", nullable = true, length = -1)
    private String content;

    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;
}