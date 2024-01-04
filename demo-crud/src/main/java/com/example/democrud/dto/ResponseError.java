package com.example.democrud.dto;

import java.time.LocalDateTime;

public record ResponseError(Integer statusCode, String message, LocalDateTime time) {}
