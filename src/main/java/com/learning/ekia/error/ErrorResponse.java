package com.learning.ekia.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(

        LocalDateTime timestamp,

        int status,

        String errorCode,

        String message,

        String path,

        String correlationId,

        Map<String, String> validationErrors
) {
}