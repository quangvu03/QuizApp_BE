package com.example.configurations;
import com.example.dtos.AccountDTO;
import com.example.dtos.QuizDTO;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.entities.Account;
import com.example.entities.Quiz;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Singleton
@Mapper(componentModel = "jsr330")
public interface QuizMapper {

    QuizDTO toDTO(Quiz quiz);

    Quiz toEntity(QuizDTO quizDTO);

    List<QuizDTO> toDTOList(List<Quiz> quizzes);
    List<Quiz> toEntityList(List<QuizDTO> quizDTOs);
    getListQuizDTO toGetListQuizDTO(Quiz quiz);
    List<getListQuizDTO> toGetListQuizDTOList(List<Quiz> quizzes);
}
