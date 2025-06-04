package com.example.backend.security.role;

import com.example.backend.security.permission.PermissionMapperService;
import org.springframework.stereotype.Service;

@Service
record RoleMapperService(
        PermissionMapperService permissionMapperService
) {

    RoleDto convertToDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .permissions(role.getPermissions().stream()
                        .map(permissionMapperService::convertToDto)
                        .toList())
                .build();
    }

    Role convertToEntity(RoleDto roleDto) {
        return Role.builder()
                .name(roleDto.name())
                .permissions(roleDto.permissions().stream()
                        .map(permissionMapperService::convertToEntity)
                        .toList())
                .build();
    }

    Role convertCreateRequestToEntity(RoleCreateDto roleCreateDto) {
        return Role.builder()
                .name(roleCreateDto.getName())
                .build();
    }

    void updateRoleFromDto(Role role, RoleUpdateDto roleUpdateDto) {
        role.setName(roleUpdateDto.getName());
    }
}