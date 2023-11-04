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
@Constraint(validatedBy = GenderCheckValidator.class)
@Documented
public @interface GenderCheck {

    String message() default "error.gender.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}