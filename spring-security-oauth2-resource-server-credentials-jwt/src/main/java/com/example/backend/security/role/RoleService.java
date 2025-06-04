package com.example.backend.security.role;

import java.util.List;
import java.util.UUID;

/**
 * @since 0.0.1
 * @see RoleServiceImpl
 */
public interface RoleService {
    Role findRoleByName(String name);
    List<RoleDto> findAllRoles();
    RoleDto findRoleById(UUID id);
    RoleDto createRole(RoleCreateDto roleCreateDto);
    RoleDto updateRoleById(UUID id, RoleUpdateDto roleUpdateDto);
    void deleteRoleById(UUID id);
}