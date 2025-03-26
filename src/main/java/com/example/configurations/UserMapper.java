package com.example.configurations;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "jakarta")
public interface UserMapper {

    @Mapping(source = "dateOfBirth", target = "dateOfBirth", qualifiedByName = "dateToLocalDate")
    AccountDTO toDTO(Account user);

    @Mapping(source = "dateOfBirth", target = "dateOfBirth", qualifiedByName = "localDateToDate")
    Account toEntity(AccountDTO userDTO);

    // Dùng @Named để gọi trong @Mapping
    @org.mapstruct.Named("dateToLocalDate")
    default LocalDate dateToLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    @org.mapstruct.Named("localDateToDate")
    default Date localDateToDate(LocalDate localDate) {
        return localDate != null ? Date.valueOf(localDate) : null;
    }
}
