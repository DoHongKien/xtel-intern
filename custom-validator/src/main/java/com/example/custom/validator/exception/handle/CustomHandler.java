package com.example.custom.validator.exception.handle;

import com.example.custom.validator.exception.ExistsEmployeeCodeException;
import com.example.custom.validator.exception.IdNotFoundException;
import com.example.custom.validator.model.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdNotFoundException.class)
    public StatusResponse handleIdNotFoundException(IdNotFoundException e) {
        return StatusResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ExistsEmployeeCodeException.class)
    public StatusResponse handleExistsEmployeeCodeException(ExistsEmployeeCodeException e) {
        return StatusResponse.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> result = new HashMap<>();
        List<FieldError> errors = e.getFieldErrors();

        for(FieldError error : errors) {
            result.put(error.getField(), error.getDefaultMessage());
        }
        return result;
    }
}
