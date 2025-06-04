package com.example.backend.common.payload.exception;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message, String[] args) {
        super(message, args);
    }
}