package com.example.backend.security.jwt;

import com.example.backend.security.token.TokenType;
import com.example.backend.security.user.User;

import java.util.UUID;

/**
 * @since 0.0.1
 * @see JwtServiceImpl
 */
public interface JwtService {
    String createJwt(User user, TokenType tokenType);
    String extractEmailFromJwt(String jwt, TokenType tokenType);
    UUID extractIdFromJwt(String jwt, TokenType tokenType);
    UUID extractSubjectFromJwt(String jwt, TokenType tokenType);
    boolean isJwtValid(String jwt, TokenType tokenType);
}