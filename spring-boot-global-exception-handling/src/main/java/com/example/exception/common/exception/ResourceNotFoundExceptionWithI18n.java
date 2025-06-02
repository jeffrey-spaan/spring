package com.example.exception.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFoundExceptionWithI18n extends RuntimeException {
    private final String message;
    private final String[] args;
}