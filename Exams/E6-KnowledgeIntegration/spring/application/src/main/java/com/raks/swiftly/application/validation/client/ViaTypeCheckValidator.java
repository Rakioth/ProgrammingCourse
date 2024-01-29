package com.raks.swiftly.application.validation.client;

import com.raks.swiftly.domain.model.dto.ViaTypeDto;
import com.raks.swiftly.domain.port.spi.ViaTypeJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ViaTypeCheckValidator implements ConstraintValidator<ViaTypeCheck, String> {

    private final ViaTypeJpaPort _viaTypeService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return _viaTypeService.getAll()
                              .stream()
                              .map(ViaTypeDto::getCode)
                              .anyMatch(code -> code.equals(value));
    }

}