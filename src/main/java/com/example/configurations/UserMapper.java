package com.example.configurations;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "jsr330", uses = DateMapper.class)
@Singleton
public interface UserMapper {
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    AccountDTO toDTO(Account user);

    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    Account toEntity(AccountDTO userDTO);
}