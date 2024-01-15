package com.example.paymentwithmultithread.exception.handle;

import com.example.paymentwithmultithread.exception.IdNotFoundException;
import com.example.paymentwithmultithread.exception.OutOfStockException;
import com.example.paymentwithmultithread.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException e) {
        ExceptionResponse response = createResponseStatus(HttpStatus.NOT_FOUND, e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> handleOutOfStockException(OutOfStockException e) {
        ExceptionResponse response = createResponseStatus(HttpStatus.NO_CONTENT, e);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    private ExceptionResponse createResponseStatus(HttpStatus status, Exception e) {
        return ExceptionResponse.builder()
                .statusCode(status.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
