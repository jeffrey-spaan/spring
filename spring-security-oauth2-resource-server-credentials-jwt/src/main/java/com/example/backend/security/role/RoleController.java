package com.example.backend.security.role;

import com.example.backend.common.constant.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constant.Request.ROLES)
@RequiredArgsConstructor
class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    ResponseEntity<List<RoleDto>> getAllRoles() {
        return new ResponseEntity<>(
                roleService.findAllRoles(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    ResponseEntity<RoleDto> getRoleById(@PathVariable UUID id) {
        return new ResponseEntity<>(
                roleService.findRoleById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_CREATE + "')")
    ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleCreateDto roleCreateDto) {
        RoleRequestNormalizer.normalize(roleCreateDto);
        return new ResponseEntity<>(
                roleService.createRole(roleCreateDto),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_UPDATE + "')")
    ResponseEntity<RoleDto> updateRoleById(@PathVariable UUID id, @Valid @RequestBody RoleUpdateDto roleUpdateDto) {
        RoleRequestNormalizer.normalize(roleUpdateDto);
        return new ResponseEntity<>(
                roleService.updateRoleById(id, roleUpdateDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_DELETE + "')")
    ResponseEntity<Void> deleteRoleById(@PathVariable UUID id) {
        roleService.deleteRoleById(id);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }
}