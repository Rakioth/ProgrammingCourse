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
@Constraint(validatedBy = CardTypeCheckValidator.class)
@Documented
public @interface CardTypeCheck {

    String message() default "error.card-type.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}