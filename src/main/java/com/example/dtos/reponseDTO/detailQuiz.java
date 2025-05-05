package com.example.dtos.reponseDTO;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.sql.Timestamp;

@Serdeable
@Data
public class detailQuiz {
    private long id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private String image;
    private String username;
    private int numberQuestion;
    private String avataUser;
    private long numberfavorite;

    public detailQuiz(long id, String title, String content, Timestamp createdAt, String image, String username, int numberQuestion, String avataUser) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.image = image;
        this.username = username;
        this.numberQuestion = numberQuestion;
        this.avataUser = avataUser;
    }

    public detailQuiz(long id, String title, String content, Timestamp createdAt, String image, String username, int numberQuestion, String avataUser, long numberfavorite) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.image = image;
        this.username = username;
        this.numberQuestion = numberQuestion;
        this.avataUser = avataUser;
        this.numberfavorite = numberfavorite;
    }

    public long getNumberfavorite() {
        return numberfavorite;
    }

    public void setNumberfavorite(int numberfavorite) {
        this.numberfavorite = numberfavorite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getAvataUser() {
        return avataUser;
    }

    public void setAvataUser(String avataUser) {
        this.avataUser = avataUser;
    }
}
