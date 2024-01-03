package com.exception.handling.exception.handling;

import com.exception.handling.exception.ConflictException;
import com.exception.handling.exception.IdNotFoundException;
import com.exception.handling.exception.UsernameNotFoundException;
import com.exception.handling.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ControllerAdvice
public class HandlingGlobalException {

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseError> handlingConflictException(ConflictException e) {
        ResponseError error = new ResponseError(HttpStatus.CONFLICT.value(), e.getMessage(), getNow());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> handlingIdNotFoundException(IdNotFoundException e) {
        ResponseError error = new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage(), getNow());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> handlingUsernameNotFoundException(UsernameNotFoundException e) {
        ResponseError error = new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage(), getNow());
        return ResponseEntity.ok(error);
    }

    private LocalDateTime getNow() {
        return LocalDate.now().atTime(LocalTime.now());
    }
}
