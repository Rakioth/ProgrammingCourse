package com.raks.swiftly.application.validation.login;

import com.raks.swiftly.domain.port.spi.UserJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailCheckValidator implements ConstraintValidator<EmailCheck, String> {

    private final UserJpaPort _userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return _userService.checkIfEmailExists(value);
    }

}