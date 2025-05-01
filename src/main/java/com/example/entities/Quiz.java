package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Serdeable
@Entity
@Table(name = "quiz", catalog = "")
public class Quiz {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "userId", nullable = true)
    private Long userId;

    @Column(name = "title", nullable = true, length = 255)
    private String title;

    @Column(name = "createdAt", nullable = true)
    private Timestamp createdAt;

    @Column(name = "content", nullable = true, length = -1)
    private String content;

    @Column(name = "level", nullable = true)
    private Integer level;

    @Column(name = "image", nullable = true)
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz that = (Quiz) o;
        return id == that.id && Objects.equals(userId, that.userId) && Objects.equals(title, that.title) && Objects.equals(createdAt, that.createdAt) && Objects.equals(content, that.content) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, createdAt, content, level);
    }
}
