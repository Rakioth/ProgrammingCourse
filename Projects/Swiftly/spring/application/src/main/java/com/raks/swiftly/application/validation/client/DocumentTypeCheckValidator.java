package com.raks.swiftly.application.validation.client;

import com.raks.swiftly.domain.model.dto.ClientDocumentDto;
import com.raks.swiftly.domain.port.spi.ClientDocumentJpaPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DocumentTypeCheckValidator implements ConstraintValidator<DocumentTypeCheck, String> {

    private final ClientDocumentJpaPort _clientDocumentService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return _clientDocumentService.getAll()
                                     .stream()
                                     .map(ClientDocumentDto::getCode)
                                     .anyMatch(code -> code.equals(value));
    }

}