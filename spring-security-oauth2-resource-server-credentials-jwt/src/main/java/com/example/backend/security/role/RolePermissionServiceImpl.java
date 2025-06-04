package com.example.backend.security.role;

import com.example.backend.common.constant.Constant;
import com.example.backend.common.util.StringUtil;
import com.example.backend.security.permission.Permission;
import com.example.backend.security.permission.PermissionCreateDto;
import com.example.backend.security.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class RolePermissionServiceImpl {

    private final PermissionService permissionService;

    @Value("${permission.actions}")
    private String[] actions;

    public List<Permission> createPermissionsForRole(RoleCreateDto roleCreateDto) {
        List<Permission> permissions = new ArrayList<>();
        for (String action : actions) {
            String roleName = StringUtil.replaceText(roleCreateDto.getName(), Constant.Security.Role.ROLE_PREFIX, "").toLowerCase() + ":" + action;
            Permission permission = permissionService.findPermissionByName(roleName);
            if (permission == null) {
                permissions.add(permissionService.createPermissionEntity(
                        new PermissionCreateDto(roleName, List.of(roleCreateDto))));
            } else {
                permissions.add(permission);
            }
        }
        permissionService.saveAllPermissions(permissions);
        return permissions;
    }
}