package com.raks.swiftly.application.handler;

import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice("com.raks.swiftly.application.api")
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getFieldErrors().forEach(fieldError -> {
            String field   = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            if (errors.containsKey(field)) {
                errors.get(field).add(message);
            } else {
                List<String> errorMessages = new ArrayList<>();
                errorMessages.add(message);
                errors.put(field, errorMessages);
            }
        });

        return new ResponseEntity<>(errors, headers, status);
    }

}