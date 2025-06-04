package com.example.backend.security.config;

import com.example.backend.common.http.HttpRequestService;
import com.example.backend.security.cookie.CookieService;
import com.example.backend.security.jwt.JwtService;
import com.example.backend.security.token.TokenService;
import com.example.backend.security.token.TokenType;
import com.example.backend.security.user.User;
import com.example.backend.security.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * CustomLogoutHandler is responsible for handling the logout process.
 * It invalidates all tokens associated with the user and clears the cookies.
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link CookieService}: This service is used to manage cookies, specifically for cleaning access and refresh tokens.</li>
 *   <li>{@link HttpRequestService}: This service is used to extract the access token from the request.</li>
 *   <li>{@link JwtService}: This service is used to extract the user ID from the JWT access token.</li>
 *   <li>{@link TokenService}: This service is used to invalidate all tokens associated with the user.</li>
 * </ul>
 *
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Component}: Marks this class as a Spring component.</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
record CustomLogoutHandler(
        CookieService cookieService,
        HttpRequestService httpRequestService,
        JwtService jwtService,
        TokenService tokenService,
        UserService userService
) implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Invalidate all tokens for the user
        String accessToken = httpRequestService.extractAccessTokenFromAuthorizationHeader();
        UUID userId = jwtService.extractSubjectFromJwt(accessToken, TokenType.ACCESS);
        tokenService.invalidateAllTokensByUserId(userId);

        // Disable the user account
        User user = userService.findById(userId);

        if (user.isEnabled()) {
            userService.setUserEnabled(user, false);
        }

        // Clear the access and refresh tokens from the request and response
        response.setHeader(HttpHeaders.AUTHORIZATION, null);
        response.addHeader(HttpHeaders.SET_COOKIE, cookieService.getCleanRefreshTokenCookie().toString());
    }
}