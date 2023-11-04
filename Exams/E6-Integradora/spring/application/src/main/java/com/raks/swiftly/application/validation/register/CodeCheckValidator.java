package com.raks.swiftly.application.validation.register;

import com.raks.swiftly.application.api.request.VerifyRequest;
import com.raks.swiftly.domain.model.dto.ConfirmationTokenDto;
import com.raks.swiftly.domain.port.spi.ConfirmationTokenJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CodeCheckValidator implements ConstraintValidator<CodeCheck, VerifyRequest> {

    private final ConfirmationTokenJpaPort _confirmationTokenServicePort;

    @Override
    public boolean isValid(VerifyRequest value, ConstraintValidatorContext context) {
        ConfirmationTokenDto confirmationTokenDto = _confirmationTokenServicePort.read(value.getEmail());

        if (confirmationTokenDto.isExpired()) {
            context.buildConstraintViolationWithTemplate("error.code.expired")
                   .addPropertyNode("code")
                   .addConstraintViolation();
            return false;
        }

        if (!confirmationTokenDto.getToken().equals(value.getCode())) {
            context.buildConstraintViolationWithTemplate("error.code.check")
                   .addPropertyNode("code")
                   .addConstraintViolation();
            return false;
        }

        return true;
    }

}
