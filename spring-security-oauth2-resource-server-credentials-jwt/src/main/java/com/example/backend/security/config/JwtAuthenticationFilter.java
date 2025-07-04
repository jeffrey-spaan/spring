package com.example.backend.security.config;

import com.example.backend.common.http.HttpRequestService;
import com.example.backend.common.payload.exception.InvalidAuthenticationException;
import com.example.backend.security.jwt.JwtService;
import com.example.backend.security.token.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter that checks if the JWT token is valid and if the user is authenticated.
 * If the token is valid, the user is authenticated and the request is allowed to pass through.
 *
 * <p>Dependencies:</p>
 * <ul>
 *     <li>{@link HttpRequestService}: This class provides operations related to HTTP requests. It is used to retrieve the access token from the request.</li>
 *     <li>{@link JwtService}: This class is responsible for operations related to JSON Web Tokens (JWTs). This includes generating, validating, and parsing JWTs.</li>
 *     <li>{@link UserDetailsService}: This interface is used to retrieve user-related data. It is used by the {@link AuthenticationManager} to authenticate a user.</li>
 * </ul>
 *
 * <p>Annotations:</p>
 * <ul>
 *     <li>{@code @Component}: This annotation marks this class as a Spring component that will be autodetected during classpath scanning.</li>
 *     <li>{@code @RequiredArgsConstructor}: This is a Lombok annotation to automatically generate a constructor with required arguments. Required arguments are final fields and fields with constraints such as @NonNull.</li>
 * </ul>
 *
 * @since 0.0.1
 * @see OncePerRequestFilter
 */
@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final HttpRequestService httpRequestService;
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    String accessToken = httpRequestService.extractAccessTokenFromAuthorizationHeader();
    String refreshToken = httpRequestService.extractRefreshTokenFromCookie();

    if ((accessToken == null || refreshToken == null) && request.getRequestURI().startsWith("/api/v1/auth")) {
      filterChain.doFilter(request, response);
      return;
    }

    if (!jwtService.isJwtValid(accessToken, TokenType.ACCESS)) {
      throw new InvalidAuthenticationException("jwt.auth.invalid", null);
    }

    if (SecurityContextHolder.getContext().getAuthentication() == null
            && jwtService.isJwtValid(accessToken, TokenType.ACCESS)) {
      String userEmail = jwtService.extractEmailFromJwt(accessToken, TokenType.ACCESS);
      setAuthentication(userEmail, request);
    }

    filterChain.doFilter(request, response);
  }

  private void setAuthentication(String userEmail, HttpServletRequest request) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authToken);
  }
}