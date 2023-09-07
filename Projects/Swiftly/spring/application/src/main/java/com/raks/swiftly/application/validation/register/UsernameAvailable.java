package com.raks.swiftly.application.validation.register;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UsernameAvailableValidator.class)
@Documented
public @interface UsernameAvailable {
    String message() default "error.username.available";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}