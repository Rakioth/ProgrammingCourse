package com.raks.swiftly.application.validation.client;

import com.raks.swiftly.domain.model.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GenderCheckValidator implements ConstraintValidator<GenderCheck, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(Gender.values())
                     .map(Gender::name)
                     .anyMatch(name -> name.equals(value));
    }

}