package com.example.configurations;

import com.example.dtos.AccountDTO;
import com.example.dtos.TakeDTO;
import com.example.entities.Account;
import com.example.entities.Take;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Singleton
@Mapper(componentModel = "jsr330", uses = DateMapper.class)
public interface TakeMapper {
    com.example.configurations.TakeMapper INSTANCE = Mappers.getMapper(com.example.configurations.TakeMapper.class);

    TakeDTO toDTO(Take take);

    Take toEntity(TakeDTO takeDTO);

}

