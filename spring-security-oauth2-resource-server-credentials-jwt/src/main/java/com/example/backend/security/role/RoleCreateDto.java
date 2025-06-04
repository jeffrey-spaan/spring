package com.example.backend.security.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.backend.common.constant.ValidationConstant.Role.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateDto {

        @NotBlank(message = NAME_NOT_BLANK_ERROR)
        @Size(min = NAME_MIN_CHAR, max = NAME_MAX_CHAR, message = NAME_SIZE_ERROR)
        @Pattern(regexp = NAME_PATTERN, message = NAME_PATTERN_ERROR)
        private String name;
}