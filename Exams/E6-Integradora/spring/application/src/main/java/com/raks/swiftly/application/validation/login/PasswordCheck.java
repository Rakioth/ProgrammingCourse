package com.raks.swiftly.application.validation.login;//package com.swiftly.application.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordCheckValidator.class)
@Documented
public @interface PasswordCheck {

    String message() default "error.password.check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}