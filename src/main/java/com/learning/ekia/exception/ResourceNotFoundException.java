package com.learning.ekia.exception;

import com.learning.ekia.error.ErrorCode;

public class ResourceNotFoundException extends EKIAException {

    public ResourceNotFoundException(String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND, message);
    }
}