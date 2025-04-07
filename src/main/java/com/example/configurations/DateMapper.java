package com.example.configurations;

import org.mapstruct.Named;
import java.sql.Date;
import java.time.LocalDate;

public class DateMapper {

    @Named("dateToLocalDate")
    public LocalDate convertToLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    @Named("localDateToDate")
    public Date convertToDate(LocalDate localDate) {
        return localDate != null ? Date.valueOf(localDate) : null;
    }
}