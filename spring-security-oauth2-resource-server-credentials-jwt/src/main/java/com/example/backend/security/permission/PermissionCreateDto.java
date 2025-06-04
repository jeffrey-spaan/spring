package com.example.backend.security.permission;

import com.example.backend.security.role.RoleCreateDto;

import java.util.List;

public record PermissionCreateDto(
        String name,
        List<RoleCreateDto> roles
) {
}