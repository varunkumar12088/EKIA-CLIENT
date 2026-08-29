package com.learning.ekia.handler;

import com.learning.ekia.error.ErrorCode;
import com.learning.ekia.error.ErrorResponse;
import com.learning.ekia.exception.EKIAException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    // =========================================
    // EKIA CUSTOM EXCEPTIONS
    // =========================================

    @ExceptionHandler(EKIAException.class)
    public ResponseEntity<ErrorResponse> handleEkiaException(EKIAException exception, HttpServletRequest request) {
        ErrorCode errorCode = exception.getErrorCode();
        log.error("EKIA exception occurred. errorCode={}, message={}", errorCode.getCode(), exception.getMessage(), exception);
        ErrorResponse response = buildErrorResponse(errorCode, exception.getMessage(), request.getRequestURI(), null);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }


    // =========================================
    // VALIDATION ERRORS
    // =========================================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Map<String, String> validationErrors = exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                (first, second) -> first, LinkedHashMap::new)
                        );

        log.warn("Validation failed: {}", validationErrors);

        ErrorResponse response = buildErrorResponse(ErrorCode.VALIDATION_ERROR, "Request validation failed", request.getRequestURI(), validationErrors);

        return ResponseEntity.badRequest().body(response);
    }


    // =========================================
    // ILLEGAL ARGUMENT
    // =========================================

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {

        log.warn("Invalid request: {}", exception.getMessage());

        ErrorResponse response = buildErrorResponse(ErrorCode.INVALID_REQUEST, exception.getMessage(), request.getRequestURI(), null);

        return ResponseEntity.badRequest().body(response);
    }


    // =========================================
    // UNKNOWN ERROR
    // =========================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception exception, HttpServletRequest request) {

        log.error("Unexpected error occurred", exception);

        ErrorResponse response = buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getDefaultMessage(), request.getRequestURI(), null);

        return ResponseEntity.status(500).body(response);
    }


    // =========================================
    // COMMON RESPONSE BUILDER
    // =========================================

    private ErrorResponse buildErrorResponse(ErrorCode errorCode, String message, String path, Map<String, String> validationErrors) {

        return new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                message,
                path,
                MDC.get("correlationId"),
                validationErrors
        );
    }
}