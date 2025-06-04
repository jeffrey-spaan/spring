package com.example.backend.security.role;

import com.example.backend.security.permission.PermissionDto;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record RoleDto(
        UUID id,
        String name,
        List<PermissionDto> permissions
) {
}