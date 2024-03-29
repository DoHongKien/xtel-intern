package com.example.custom.validator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatusResponse {

    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
}
