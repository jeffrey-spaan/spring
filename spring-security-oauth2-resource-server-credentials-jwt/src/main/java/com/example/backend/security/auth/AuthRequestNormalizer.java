package com.example.backend.security.auth;

/**
 * Utility class to normalize the fields of the authentication request objects.
 *
 * @since 0.0.1
 */
class AuthRequestNormalizer {

    private AuthRequestNormalizer() {
        // Private constructor to prevent instantiation
    }

    public static void normalize(AuthLoginDto loginDto) {
        if (loginDto.getEmailOrUsername() != null) loginDto.setEmailOrUsername(loginDto.getEmailOrUsername().toLowerCase());
    }

    public static void normalize(AuthRegisterDto authRegisterDto) {
        if (authRegisterDto.getEmail() != null) authRegisterDto.setEmail(authRegisterDto.getEmail().toLowerCase());
        if (authRegisterDto.getUsername() != null) authRegisterDto.setUsername(authRegisterDto.getUsername().toLowerCase());
    }
}