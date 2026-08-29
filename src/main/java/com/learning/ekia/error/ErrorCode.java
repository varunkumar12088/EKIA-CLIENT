package com.learning.ekia.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // =========================================
    // CLIENT ERRORS
    // =========================================

    INVALID_REQUEST("EKIA-400-001", "Invalid request", HttpStatus.BAD_REQUEST),

    VALIDATION_ERROR("EKIA-400-002", "Request validation failed", HttpStatus.BAD_REQUEST),


    // =========================================
    // NOT FOUND
    // =========================================

    RESOURCE_NOT_FOUND("EKIA-404-001", "Requested resource was not found", HttpStatus.NOT_FOUND),


    // =========================================
    // MCP ERRORS
    // =========================================

    MCP_CONNECTION_ERROR("EKIA-MCP-001", "Unable to connect to MCP server", HttpStatus.SERVICE_UNAVAILABLE),

    MCP_TOOL_EXECUTION_ERROR("EKIA-MCP-002", "MCP tool execution failed", HttpStatus.INTERNAL_SERVER_ERROR),


    // =========================================
    // LLM ERRORS
    // =========================================

    LLM_PROVIDER_ERROR("EKIA-LLM-001", "LLM provider request failed", HttpStatus.BAD_GATEWAY),

    LLM_TIMEOUT_ERROR("EKIA-LLM-002", "LLM request timed out", HttpStatus.GATEWAY_TIMEOUT),


    // =========================================
    // INTERNAL ERRORS
    // =========================================

    INTERNAL_SERVER_ERROR("EKIA-500-001", "An unexpected internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String code;

    private final String defaultMessage;

    private final HttpStatus httpStatus;


    ErrorCode(String code, String defaultMessage, HttpStatus httpStatus) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }


    public String getCode() {
        return code;
    }


    public String getDefaultMessage() {
        return defaultMessage;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}