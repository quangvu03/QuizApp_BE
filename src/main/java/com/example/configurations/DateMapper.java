package com.example.configurations;

import jakarta.inject.Singleton;
import org.mapstruct.Named;

import java.sql.Date;
import java.time.LocalDate;

@Singleton
public class DateMapper {
    @Named("dateToLocalDate")
    public LocalDate dateToLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    @Named("localDateToDate")
    public Date localDateToDate(LocalDate localDate) {
        return localDate != null ? Date.valueOf(localDate) : null;
    }
}