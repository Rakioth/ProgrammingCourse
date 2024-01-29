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
@Constraint(validatedBy = RemovedAccountValidator.class)
@Documented
public @interface RemovedAccount {

    String message() default "error.username.removed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}