package com.raks.swiftly.application.validation.client;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = DocumentTypeCheckValidator.class)
@Documented
public @interface DocumentTypeCheck {
    String message() default "error.document-type.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}