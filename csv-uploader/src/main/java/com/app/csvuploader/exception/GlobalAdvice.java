package com.app.csvuploader.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Optional;

@ControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            final HttpServletRequest request, final ResourceNotFoundException e) {
        final var status = HttpStatus.NOT_FOUND;
        final var message = Optional.of(e).map(RuntimeException::getMessage)
                .orElse("The request was not understood by the server");
        final var error = new ErrorResponse(request.getRequestURI(),
                status.getReasonPhrase(),
                status.value(),
                message,
                ZonedDateTime.now());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(
            final HttpServletRequest request, final RuntimeException e) {
        final var status = HttpStatus.BAD_REQUEST;
        final var message = Optional.of(e).map(RuntimeException::getMessage)
                .orElse("The request was not understood by the server");
        final var error = new ErrorResponse(request.getRequestURI(),
                status.getReasonPhrase(),
                status.value(),
                message,
                ZonedDateTime.now());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            final HttpServletRequest request, final Exception e) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        final var message = Optional.of(e).map(Exception::getMessage).orElse("");
        final var error = new ErrorResponse(request.getRequestURI(),
                status.getReasonPhrase(),
                status.value(),
                message,
                ZonedDateTime.now());
        return ResponseEntity.status(status).body(error);
    }
}
