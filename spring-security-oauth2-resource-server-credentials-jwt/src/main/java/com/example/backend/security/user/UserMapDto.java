package com.example.backend.security.user;

import lombok.Builder;

@Builder
public record UserMapDto(
        String key,
        Long value
) {
}