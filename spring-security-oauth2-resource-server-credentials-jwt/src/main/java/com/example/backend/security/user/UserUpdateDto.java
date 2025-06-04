package com.example.backend.security.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.example.backend.common.constant.ValidationConstant.User.*;

public record UserUpdateDto(

        @NotBlank(message = USERNAME_NOT_BLANK_ERROR)
        @Size(min = USERNAME_MIN_CHAR, max = USERNAME_MAX_CHAR, message = USERNAME_SIZE_ERROR)
        @Pattern(regexp = USERNAME_PATTERN, message = USERNAME_PATTERN_ERROR)
        String username,

        @NotBlank(message = EMAIL_NOT_BLANK_ERROR)
        @Size(min = EMAIL_MIN_CHAR, max = EMAIL_MAX_CHAR, message = EMAIL_SIZE_ERROR)
        @Pattern(regexp = EMAIL_PATTERN, message = EMAIL_PATTERN_ERROR)
        String email,

        @NotBlank(message = PASSWORD_NOT_BLANK_ERROR)
        @Size(min = PASSWORD_MIN_CHAR, max = PASSWORD_MAX_CHAR, message = PASSWORD_SIZE_ERROR)
        @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_ERROR)
        String password,

        @NotBlank(message = ROLE_NOT_BLANK_ERROR)
        @Size(min = ROLE_MIN_CHAR, max = ROLE_MAX_CHAR, message = ROLE_SIZE_ERROR)
        @Pattern(regexp = ROLE_PATTERN, message = ROLE_PATTERN_ERROR)
        String role
) {
}