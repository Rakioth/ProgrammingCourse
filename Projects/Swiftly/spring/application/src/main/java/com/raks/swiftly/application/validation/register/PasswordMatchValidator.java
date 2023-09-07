package com.raks.swiftly.application.validation.register;

import com.raks.swiftly.application.api.request.AuthRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, AuthRequest> {

    @Override
    public boolean isValid(AuthRequest value, ConstraintValidatorContext context) {
        if (!value.getPassword().equals(value.getPasswordConfirm())) {
            context.buildConstraintViolationWithTemplate("error.password.match")
                   .addPropertyNode("passwordConfirm")
                   .addConstraintViolation();
            return false;
        }
        return true;
    }

}