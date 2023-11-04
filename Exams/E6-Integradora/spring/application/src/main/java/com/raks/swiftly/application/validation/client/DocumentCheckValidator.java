package com.raks.swiftly.application.validation.client;

import com.raks.swiftly.application.api.request.ClientDetailsRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class DocumentCheckValidator implements ConstraintValidator<DocumentCheck, ClientDetailsRequest> {

    @Override
    public boolean isValid(ClientDetailsRequest value, ConstraintValidatorContext context) {
        if (value.getDocumentTypeCode() == null) return false;

        boolean isValid;
        String  errorMessage;

        switch (value.getDocumentTypeCode()) {
            case "dni" -> {
                isValid      = Pattern.compile("\\d{8}[a-zA-Z]").matcher(value.getDocument()).matches();
                errorMessage = "error.document.dni";
            }
            case "nie" -> {
                isValid      = Pattern.compile("[XYZxyz]\\d{7,8}[a-zA-Z]").matcher(value.getDocument()).matches();
                errorMessage = "error.document.nie";
            }
            case "passport" -> {
                isValid      = Pattern.compile("[a-zA-Z]{2}\\d{6,7}[a-zA-Z]?").matcher(value.getDocument()).matches();
                errorMessage = "error.document.passport";
            }
            case "social" -> {
                isValid      = Pattern.compile("\\d{9}").matcher(value.getDocument()).matches();
                errorMessage = "error.document.social";
            }
            default -> {
                isValid      = false;
                errorMessage = "error.document.check";
            }
        }

        if (!isValid) {
            context.buildConstraintViolationWithTemplate(errorMessage)
                   .addPropertyNode("document")
                   .addConstraintViolation();
        }

        return isValid;
    }

}