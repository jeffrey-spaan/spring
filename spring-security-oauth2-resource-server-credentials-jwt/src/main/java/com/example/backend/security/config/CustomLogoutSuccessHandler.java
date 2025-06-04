package com.example.backend.security.config;

import com.nimbusds.jose.shaded.gson.Gson;
import com.example.backend.common.constant.HttpConstant;
import com.example.backend.common.http.HttpRequestService;
import com.example.backend.common.i18n.I18nService;
import com.example.backend.common.payload.response.OkResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * CustomLogoutSuccessHandler is a Spring component that handles logout success events.
 * It sends a JSON response with a success message and the request URI.
 *
 * <p>Dependencies</p>
 * <ul>
 *   <li>{@link HttpRequestService}: This service is used to extract the access token from the request.</li>
 *   <li>{@link I18nService}: This service is used to get internationalized messages.</li>
 * </ul>
 *
 * <p>Annotations</p>
 * <ul>
 *   <li>{@code @Component}: Marks this class as a Spring component.</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
record CustomLogoutSuccessHandler(
        HttpRequestService httpRequestService,
        I18nService i18nService
) implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding(HttpConstant.Body.UTF_8);

        // Create response body
        OkResponse okResponse = new OkResponse();
        okResponse.setDetail(i18nService.getMessage("logout.success"));
        okResponse.setInstance(httpRequestService.getRequestUri());

        // Convert response body to JSON string
        Gson gson = new Gson();
        String serializedResponse = gson.toJson(okResponse);

        // Write response body into the response using the writer
        response.getWriter().write(serializedResponse);
    }
}