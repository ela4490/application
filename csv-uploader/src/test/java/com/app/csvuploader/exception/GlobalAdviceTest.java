package com.app.csvuploader.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

final class GlobalAdviceTest {
    private static final HttpServletRequest REQUEST = mock(HttpServletRequest.class);
    private GlobalAdvice globalAdvice;

    @BeforeEach
    void setUp() {
        globalAdvice = new GlobalAdvice();
    }

    @Test
    void map_resource_not_found_exception() {
        // Arrange
        final var error = new ResourceNotFoundException("Resource not found");

        // Act
        final var actual = globalAdvice.handleResourceNotFoundException(REQUEST, error);

        // Assert
        assertThat(actual).extracting(HttpEntity::getBody)
                .extracting(ErrorResponse::error,
                        ErrorResponse::erroCode,
                        ErrorResponse::message,
                        ErrorResponse::path)
                .contains(NOT_FOUND.getReasonPhrase(),
                        NOT_FOUND.value(),
                        "Resource not found",
                        REQUEST.getRequestURI());
        assertThat(actual)
                .extracting(HttpEntity::getBody)
                .extracting(ErrorResponse::timestamp)
                .isNotNull();
    }

    @Test
    void map_bad_request_exception() {
        // Arrange
        final var error = new IllegalArgumentException("Bad Request");

        // Act
        final var actual = globalAdvice.handleBadRequestException(REQUEST, error);

        // Assert
        assertThat(actual).extracting(HttpEntity::getBody)
                .extracting(ErrorResponse::error,
                        ErrorResponse::erroCode,
                        ErrorResponse::message,
                        ErrorResponse::path)
                .contains(BAD_REQUEST.getReasonPhrase(),
                        BAD_REQUEST.value(),
                        "Bad Request",
                        REQUEST.getRequestURI());
        assertThat(actual)
                .extracting(HttpEntity::getBody)
                .extracting(ErrorResponse::timestamp)
                .isNotNull();
    }

    @Test
    void map_exception() {
        // Arrange
        final var error = new Exception("Exception occurred");

        // Act
        final var actual = globalAdvice.handleException(REQUEST, error);

        // Assert
        assertThat(actual).extracting(HttpEntity::getBody)
                .extracting(ErrorResponse::error,
                        ErrorResponse::erroCode,
                        ErrorResponse::message,
                        ErrorResponse::path)
                .contains(INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        INTERNAL_SERVER_ERROR.value(),
                        "Exception occurred",
                        REQUEST.getRequestURI());
        assertThat(actual)
                .extracting(HttpEntity::getBody)
                .extracting(ErrorResponse::timestamp)
                .isNotNull();
    }
}