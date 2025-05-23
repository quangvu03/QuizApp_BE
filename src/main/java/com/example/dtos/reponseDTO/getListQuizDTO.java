package com.example.dtos.reponseDTO;


import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Data
@Serdeable
public class getListQuizDTO {
    private long id;
    private String title;
    private int numberquiz;
    private String image;
    private String userName;
    private String imageUser;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberquiz() {
        return numberquiz;
    }

    public void setNumberquiz(int numberquiz) {
        this.numberquiz = numberquiz;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
