package com.raks.swiftly.application.validation.login;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UsernameCheckValidator.class)
@Documented
public @interface UsernameCheck {
    String message() default "error.username.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}