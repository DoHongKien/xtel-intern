package com.exception.handling.model;

import java.time.LocalDateTime;

public record ResponseError (Integer statusCode, String message, LocalDateTime time) {}
