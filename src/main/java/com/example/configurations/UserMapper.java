package com.example.configurations;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "micronaut", uses = DateMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AccountDTO toDTO(Account user);

    Account toEntity(AccountDTO userDTO);

}
