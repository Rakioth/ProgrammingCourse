package com.raks.swiftly.application.validation.client;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = DocumentCheckValidator.class)
@Documented
public @interface DocumentCheck {

    String message() default "error.document.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}