package com.example.custom.validator.validator.custom_annotation;

import com.example.custom.validator.validator.custom_validator.EmployeeCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmployeeCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartWithEmployee {

    String message() default "Employee code must be start with EMP";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
