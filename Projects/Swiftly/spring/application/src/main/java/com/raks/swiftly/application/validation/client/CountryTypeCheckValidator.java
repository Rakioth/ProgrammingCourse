package com.raks.swiftly.application.validation.client;

import com.raks.swiftly.domain.model.dto.CountryTypeDto;
import com.raks.swiftly.domain.port.spi.CountryTypeJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CountryTypeCheckValidator implements ConstraintValidator<CountryTypeCheck, String> {

    private final CountryTypeJpaPort _countryTypeService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return _countryTypeService.getAll()
                                  .stream()
                                  .map(CountryTypeDto::getCode)
                                  .anyMatch(code -> code.equals(value));
    }

}