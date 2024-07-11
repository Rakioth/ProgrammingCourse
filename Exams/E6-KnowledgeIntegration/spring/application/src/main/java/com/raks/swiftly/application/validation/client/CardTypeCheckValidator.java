package com.raks.swiftly.application.validation.client;

import com.raks.swiftly.domain.model.dto.CardTypeDto;
import com.raks.swiftly.domain.port.spi.CardTypeJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CardTypeCheckValidator implements ConstraintValidator<CardTypeCheck, String> {

    private final CardTypeJpaPort _cardTypeService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return _cardTypeService.getAll()
                               .stream()
                               .map(CardTypeDto::getCode)
                               .anyMatch(code -> code.equals(value));
    }

}