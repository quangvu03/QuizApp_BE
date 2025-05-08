package com.example.dtos.reponseDTO;

import com.example.dtos.requestDTO.demoAnswer;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class examQuizDTO {
    private Long id;         // có thể null
    private String title;
    private String type;
    private String content;
    private int level;      // có thể null
    private List<demoAnswer> answers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public List<demoAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<demoAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "examQuizDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", level=" + level +
                ", answers=" + answers +
                '}';
    }
}

