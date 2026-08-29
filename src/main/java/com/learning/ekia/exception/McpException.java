package com.learning.ekia.exception;

import com.learning.ekia.error.ErrorCode;

public class McpException extends EKIAException {

    public McpException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public McpException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}