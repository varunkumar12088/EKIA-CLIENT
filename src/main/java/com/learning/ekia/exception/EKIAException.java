package com.learning.ekia.exception;

import com.learning.ekia.error.ErrorCode;
import lombok.Getter;

@Getter
public class EKIAException extends RuntimeException {

    private final ErrorCode errorCode;


    public EKIAException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public EKIAException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public EKIAException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}