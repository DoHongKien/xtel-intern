package com.example.custom.validator.validator.custom_validator;

import com.example.custom.validator.validator.custom_annotation.StartWithEmployee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeCodeValidator implements ConstraintValidator<StartWithEmployee, String> {

    private static final String EMPLOYEE_CODE_PREFIX = "EMP";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()) return false;
        return s.startsWith(EMPLOYEE_CODE_PREFIX);
    }
}
