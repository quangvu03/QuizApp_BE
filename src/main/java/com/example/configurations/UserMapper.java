package com.example.configurations;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Singleton
@Mapper(componentModel = "jsr330", uses = DateMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role", target = "role")
    AccountDTO toDTO(Account user);

    @Mapping(source = "role", target = "role")
    Account toEntity(AccountDTO userDTO);

}
