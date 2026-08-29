package com.learning.ekia.exception;

import com.learning.ekia.error.ErrorCode;

public class LlmException extends EKIAException {

    public LlmException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public LlmException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}