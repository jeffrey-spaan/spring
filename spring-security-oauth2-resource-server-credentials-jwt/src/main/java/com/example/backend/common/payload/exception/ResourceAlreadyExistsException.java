package com.example.backend.common.payload.exception;

public class ResourceAlreadyExistsException extends BaseException {
    public ResourceAlreadyExistsException(String message, String[] args) {
        super(message, args);
    }
}