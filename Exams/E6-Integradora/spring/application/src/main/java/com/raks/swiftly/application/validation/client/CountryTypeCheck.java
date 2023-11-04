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
@Constraint(validatedBy = CountryTypeCheckValidator.class)
@Documented
public @interface CountryTypeCheck {

    String message() default "error.country-type.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}