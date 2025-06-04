package com.example.backend.security.permission;

import org.springframework.stereotype.Service;

@Service
public record PermissionMapperService() {

    public PermissionDto convertToDto(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }

    public Permission convertToEntity(PermissionDto permissionDto) {
        return Permission.builder()
                .id(permissionDto.id())
                .name(permissionDto.name())
                .build();
    }

    public Permission convertCreateDtoToEntity(PermissionCreateDto permissionCreateDto) {
        return Permission.builder()
                .name(permissionCreateDto.name())
                .build();
    }

    public Permission convertUpdateDtoToEntity(PermissionUpdateDto permissionUpdateDto) {
        return Permission.builder()
                .name(permissionUpdateDto.name())
                .build();
    }
}