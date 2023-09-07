package com.raks.swiftly.application.validation.reset;

import com.raks.swiftly.application.api.request.ResetRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, ResetRequest> {

    @Override
    public boolean isValid(ResetRequest value, ConstraintValidatorContext context) {
        if (!value.getPassword().equals(value.getPasswordConfirm())) {
            context.buildConstraintViolationWithTemplate("error.password.match")
                   .addPropertyNode("passwordConfirm")
                   .addConstraintViolation();
            return false;
        }
        return true;
    }

}