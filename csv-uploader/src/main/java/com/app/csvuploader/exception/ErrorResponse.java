package com.app.csvuploader.exception;

import java.time.ZonedDateTime;

public record ErrorResponse(
        String path,
        String error,
        int erroCode,
        String message,
        ZonedDateTime timestamp) {}