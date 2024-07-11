package com.raks.swiftly.application.validation.block;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class BlockCheckValidator implements ConstraintValidator<BlockCheck, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.isAfter(LocalDateTime.now());
    }

}