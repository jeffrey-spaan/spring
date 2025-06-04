package com.example.backend.security.permission;

import java.util.List;
import java.util.UUID;

/**
 * @since 0.0.1
 * @see PermissionServiceImpl
 */
public interface PermissionService {
    List<PermissionDto> findAllPermissions();
    PermissionDto findPermissionById(UUID id);
    PermissionDto savePermission(PermissionCreateDto createDto);
    Permission createPermissionEntity(PermissionCreateDto createDto);
    void deletePermissionById(UUID id);
    Permission findPermissionByName(String name);
    void saveAllPermissions(List<Permission> permissions);
}
