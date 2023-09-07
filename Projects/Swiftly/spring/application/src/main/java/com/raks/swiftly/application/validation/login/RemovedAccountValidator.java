package com.raks.swiftly.application.validation.login;

import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.domain.model.enums.Role;
import com.raks.swiftly.domain.port.spi.UserJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemovedAccountValidator implements ConstraintValidator<RemovedAccount, String> {

    private final UserJpaPort _userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        UserDto dto = _userService.getOptional(value);
        if (dto == null) return true;
        return dto.getRole() != Role.DELETED;
    }

}