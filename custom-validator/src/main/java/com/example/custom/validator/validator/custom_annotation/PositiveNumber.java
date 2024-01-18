package com.example.custom.validator.validator.custom_annotation;

import com.example.custom.validator.validator.custom_validator.PositiveNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositiveNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveNumber {

    String message() default "Number must be positive and not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
