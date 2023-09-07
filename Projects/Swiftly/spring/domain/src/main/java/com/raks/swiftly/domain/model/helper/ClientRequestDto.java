package com.raks.swiftly.domain.model.helper;

import com.raks.swiftly.domain.model.enums.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientRequestDto {
    private String     surname;
    private String     type;
    private Gender     gender;
    private LocalDate  startBirthdate;
    private LocalDate  endBirthdate;
    private BigDecimal startExpenses;
    private BigDecimal endExpenses;
}