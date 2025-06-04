package com.example.backend.security.config;

import com.nimbusds.jose.shaded.gson.Gson;
import com.example.backend.common.constant.HttpConstant;
import com.example.backend.common.i18n.I18nService;
import com.example.backend.common.payload.response.UnauthorizedResponse;
import com.example.backend.security.cookie.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * CustomAuthenticationEntryPointHandler is a custom implementation of the {@link AuthenticationEntryPoint} interface.
 * It handles authentication exceptions by customizing the response sent to the client.
 * This handler is used within the {@link SecurityConfig} class to handle authentication exceptions.
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link CookieService}: This service is used to manage cookies, specifically for cleaning access and refresh tokens.</li>
 *   <li>{@link I18nService}: This service is used to retrieve localized messages.</li>
 *   <li>{@code @Value("${server.error.path}") String errorUri}: This value is injected from the application properties and represents the error path to be included in the error response.</li>
 * </ul>
 *
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Component}: Marks this class as a Spring component.</li>
 * </ul>
 *
 * @since 0.0.1
 * @see AuthenticationEntryPoint
 * @see SecurityConfig
 */
@Component
record CustomAuthenticationEntryPointHandler(
        CookieService cookieService,
        I18nService i18nService,
        @Value("${server.error.path}") String errorPath
) implements AuthenticationEntryPoint {

    /**
     * Handles an authentication exception by customizing the response sent to the client.
     *
     * @param request the {@link HttpServletRequest} object
     * @param response the {@link HttpServletResponse} object
     * @param authException the {@link AuthenticationException} thrown
     * @throws IOException if an input or output exception occurs
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(HttpConstant.Body.UTF_8);
        response.setHeader(HttpHeaders.AUTHORIZATION, null);
        response.addHeader(HttpHeaders.SET_COOKIE, cookieService.getCleanRefreshTokenCookie().toString());

        // Create response body
        UnauthorizedResponse unauthorizedResponse = new UnauthorizedResponse();
        unauthorizedResponse.setType(errorPath);
        unauthorizedResponse.setDetail(i18nService.getMessage("auth.entry.unauthorized"));
        unauthorizedResponse.setInstance(request.getRequestURI());

        Gson gson = new Gson();
        String serializedResponse = gson.toJson(unauthorizedResponse);

        // Write response body into the response using the writer
        response.getWriter().write(serializedResponse);
    }
}