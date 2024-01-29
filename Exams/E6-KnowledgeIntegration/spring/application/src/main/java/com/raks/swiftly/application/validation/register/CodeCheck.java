package com.raks.swiftly.application.validation.register;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = CodeCheckValidator.class)
@Documented
public @interface CodeCheck {

    String message() default "error.code.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
