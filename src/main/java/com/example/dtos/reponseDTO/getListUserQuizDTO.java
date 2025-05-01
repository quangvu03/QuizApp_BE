package com.example.dtos.reponseDTO;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class getListUserQuizDTO {
    private String image;
    private String username;
    private int numberquiz;


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

    public int getNumberquiz() {
        return numberquiz;
    }

    public void setNumberquiz(int numberquiz) {
        this.numberquiz = numberquiz;
    }
}
