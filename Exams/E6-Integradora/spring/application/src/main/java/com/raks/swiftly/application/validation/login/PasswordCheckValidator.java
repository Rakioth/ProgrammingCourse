package com.raks.swiftly.application.validation.login;

import com.raks.swiftly.application.api.request.LogauthRequest;
import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.domain.port.spi.UserJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordCheckValidator implements ConstraintValidator<PasswordCheck, LogauthRequest> {

    private final UserJpaPort _userService;

    @Override
    public boolean isValid(LogauthRequest value, ConstraintValidatorContext context) {
        if (!_userService.checkCredentials(
                UserDto.builder()
                       .username(value.getUsername())
                       .password(value.getPassword())
                       .build()
        )) {
            context.buildConstraintViolationWithTemplate("error.password.check")
                   .addPropertyNode("password")
                   .addConstraintViolation();
            return false;
        }
        return true;
    }

}