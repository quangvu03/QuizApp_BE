package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;
@Serdeable

@Entity
@Table(name = "quizquestion", catalog = "")
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
    
    @Column(name = "level", nullable = true)
    private Integer level;
    
    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;
    
    @Column(name = "content", nullable = true, length = -1)
    private String content;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quizquestion that = (Quizquestion) o;
        return id == that.id && Objects.equals(quizId, that.quizId) && Objects.equals(title, that.title) && Objects.equals(type, that.type) && Objects.equals(level, that.level) && Objects.equals(createdAt, that.createdAt) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, title, type, level, createdAt, content);
    }

    @Override
    public String toString() {
        return "Quizquestion{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", createdAt=" + createdAt +
                ", content='" + content + '\'' +
                '}';
    }
}
