package com.raks.swiftly.application.validation.login;

import com.raks.swiftly.application.api.request.LogauthRequest;
import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.domain.port.spi.UserJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
public class BlockAccountValidator implements ConstraintValidator<BlockAccount, LogauthRequest> {

    private final UserJpaPort _userService;

    @Override
    public boolean isValid(LogauthRequest value, ConstraintValidatorContext context) {
        UserDto dto = _userService.getByUsername(value.getUsername());

        if (dto.getReleaseBlock() != null) {
            LocalDateTime now = LocalDateTime.now();
            context.buildConstraintViolationWithTemplate(String.valueOf(ChronoUnit.MINUTES.between(now, dto.getReleaseBlock())))
                   .addPropertyNode("username")
                   .addConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.valueOf(ChronoUnit.MINUTES.between(now, dto.getReleaseBlock())))
                   .addPropertyNode("password")
                   .addConstraintViolation();
            return false;
        }

        return true;
    }

}