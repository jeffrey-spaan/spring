package com.example.backend.security.cookie;

import org.springframework.http.ResponseCookie;

/**
 * @since 0.0.1
 * @see CookieServiceImpl
 */
public interface CookieService {
    ResponseCookie generateRefreshTokenCookie(String refreshToken);
    ResponseCookie getCleanRefreshTokenCookie();
}