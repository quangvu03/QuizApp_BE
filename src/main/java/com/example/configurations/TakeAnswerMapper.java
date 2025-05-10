package com.example.configurations;

import com.example.dtos.TakeanswerDTO;
import com.example.entities.Takeanswer;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Singleton
@Mapper(componentModel = "jsr330", uses = DateMapper.class)
public interface TakeAnswerMapper {
    TakeAnswerMapper INSTANCE = Mappers.getMapper(TakeAnswerMapper.class);

    TakeanswerDTO toDTO(Takeanswer takeanswer);

    Takeanswer toEntity(TakeanswerDTO takeanswerDTO);

    List<TakeanswerDTO> toDTOList(List<Takeanswer> takeanswerList);

    List<Takeanswer> toEntityList(List<TakeanswerDTO> takeanswerDTOList);
}

