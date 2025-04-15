package com.example.configurations;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Singleton
@Mapper(componentModel = "jsr330", uses = DateMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AccountDTO toDTO(Account user);

    Account toEntity(AccountDTO userDTO);

}
