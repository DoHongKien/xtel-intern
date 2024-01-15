package com.example.paymentwithmultithread.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExceptionResponse {

    private Integer statusCode;

    private String message;

    private LocalDateTime timestamp;
}
