package com.example.backend.security.permission;

import com.example.backend.common.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constant.Request.PERMISSIONS)
@RequiredArgsConstructor
class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    ResponseEntity<List<PermissionDto>> getAllPermissions() {
        return new ResponseEntity<>(
                permissionService.findAllPermissions(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    ResponseEntity<PermissionDto> getPermissionById(@PathVariable UUID id) {
        return new ResponseEntity<>(
                permissionService.findPermissionById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_CREATE + "')")
    ResponseEntity<PermissionDto> createPermission(@RequestBody PermissionCreateDto createDto) {
        return new ResponseEntity<>(
                permissionService.savePermission(createDto),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_DELETE + "')")
    ResponseEntity<Void> deletePermissionById(@PathVariable UUID id) {
        permissionService.deletePermissionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}