package com.example.backend.common.http;

/**
 * @since 0.0.1
 * @see HttpRequestServiceImpl
 */
public interface HttpRequestService {
    String getRequestUri();
    String extractIpFromXffHeader();
    String extractAccessTokenFromAuthorizationHeader();
    String extractRefreshTokenFromCookie();
}