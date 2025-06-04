package com.example.backend.security.role;

import com.example.backend.common.constant.Constant;
import org.springframework.util.StringUtils;

class RoleRequestNormalizer {

    private RoleRequestNormalizer() {
        // Private constructor to prevent instantiation
    }

    public static void normalize(RoleCreateDto createDto) {
        String name = createDto.getName();
        if (StringUtils.hasText(name)) {
            if (name.startsWith(Constant.Security.Role.ROLE_PREFIX)) {
                createDto.setName(name.toUpperCase());
            } else {
                createDto.setName(Constant.Security.Role.ROLE_PREFIX + name.toUpperCase());
            }
        }
    }

    public static void normalize(RoleUpdateDto roleUpdateDto) {
        String name = roleUpdateDto.getName();
        if (StringUtils.hasText(name)) {
            if (name.startsWith(Constant.Security.Role.ROLE_PREFIX)) {
                roleUpdateDto.setName(name.toUpperCase());
            } else {
                roleUpdateDto.setName(Constant.Security.Role.ROLE_PREFIX + name.toUpperCase());
            }
        }
    }
}