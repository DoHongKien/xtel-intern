package com.example.custom.validator.validator.custom_validator;

import com.example.custom.validator.validator.custom_annotation.PositiveNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveNumberValidator implements ConstraintValidator<PositiveNumber, Integer> {

    @Override
    public boolean isValid(Integer number, ConstraintValidatorContext constraintValidatorContext) {
        return number != null && number > 0;
    }
}
