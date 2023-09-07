package com.raks.swiftly.application.validation.client;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthdateCheckValidator implements ConstraintValidator<BirthdateCheck, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) return true;

        LocalDate minimumDate = LocalDate.now().minusYears(18);
        return value.isBefore(minimumDate) || value.isEqual(minimumDate);
    }

}