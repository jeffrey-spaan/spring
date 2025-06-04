package com.example.backend.security.auth;

import com.example.backend.common.http.HttpRequestService;
import com.example.backend.common.i18n.I18nService;
import com.example.backend.security.user.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * Factory class that creates Authentication related instances.
 *
 * <p>Dependencies:</p>
 * <ul>
 *     <li>{@link HttpRequestService}: This class provides operations related to HTTP requests. It is used to retrieve the access token from the request.</li>
 *     <li>{@link I18nService}: This class is responsible for internationalization and localization of messages. It is used to retrieve messages from the message source.</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
public record AuthFactory(
        HttpRequestService httpRequestService,
        I18nService i18nService
) {

    /**
     * Creates an {@link AuthDto} object with the provided user details, message, JWT, and refresh token.
     *
     * @param user the user for whom the authentication response is created
     * @param message the message key to be used for the response detail
     * @param accessToken the access token to be included in the response
     * @param refreshToken the refresh token to be included in the response
     * @return an {@link AuthDto} object containing the user details, message, JWT access token, and refresh token
     */
    public AuthDto createAuthResponse(@NotNull User user, String message, String accessToken, String refreshToken) {
        AuthDto authDto = new AuthDto();
        authDto.setDetail(i18nService.getMessage(message, user.getUsername()));
        authDto.setInstance(httpRequestService != null ? httpRequestService.getRequestUri() : null);
        authDto.setAccessToken(accessToken);
        authDto.setRefreshToken(refreshToken);
        return authDto;
    }
}