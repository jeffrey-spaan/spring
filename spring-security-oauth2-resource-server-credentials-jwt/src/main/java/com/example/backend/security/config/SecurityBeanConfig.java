package com.example.backend.security.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.example.backend.common.payload.exception.ResourceNotFoundException;
import com.example.backend.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import java.time.Duration;

/**
 * Configuration class for security-related beans.
 * <p>
 * This class defines beans for authentication management, JWT encoding/decoding,
 * password encoding, and user details service.
 * </p>
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link RsaKeyProperties}: Properties for RSA key pair used in JWT encoding/decoding.</li>
 *   <li>{@link UserRepository}: Repository for user-related operations.</li>
 * </ul>
 *
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Configuration}: Marks this class as a Spring configuration class.</li>
 *   <li>{@code @RequiredArgsConstructor}: Generates a constructor with required arguments.</li>
 * </ul>
 *
 * @since 0.0.1
 * @see <a href="https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html">Spring Security: Username/Password Security</a>
 */
@Configuration
@RequiredArgsConstructor
class SecurityBeanConfig {

    private final RsaKeyProperties rsaKeys;

    private final UserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    UserDetailsService userDetailsService() {
        return emailOrUsername -> userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.by.email.or.username",
                        new String[]{emailOrUsername})
                );
    }

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();

        OAuth2TokenValidator<Jwt> withClockSkew =
                new DelegatingOAuth2TokenValidator<>(
                        new JwtTimestampValidator(Duration.ofSeconds(60)));

        jwtDecoder.setJwtValidator(withClockSkew);

        return jwtDecoder;
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey())
                .privateKey(rsaKeys.privateKey())
                .build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}