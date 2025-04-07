package com.example.configurations;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Singleton
@Mapper(componentModel = "micronaut", uses = DateMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AccountDTO toDTO(Account user);

    Account toEntity(AccountDTO userDTO);

}
