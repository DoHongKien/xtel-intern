package com.springredis.exception.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springredis.exception.IdNotFoundException;
import com.springredis.model.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StatusResponse> handlerIdNotFoundException(IdNotFoundException e) {
        int status = HttpStatus.NOT_FOUND.value();
        StatusResponse response = StatusResponse.builder()
                .statusCode(status)
                .content(e.getMessage())
                .date(getNow())
                .build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.PROCESSING)
    public ResponseEntity<StatusResponse> handlerJsonProcessingException(JsonProcessingException e) {
        int status = HttpStatus.PROCESSING.value();
        StatusResponse response = StatusResponse.builder()
                .statusCode(status)
                .content(e.getMessage())
                .date(getNow())
                .build();
        return ResponseEntity.status(status).body(response);
    }

    private LocalDateTime getNow() {
        return LocalDate.now().atTime(LocalTime.now());
    }
}
