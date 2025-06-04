package com.example.backend.security.permission;

import com.example.backend.common.payload.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
record PermissionServiceImpl(
        PermissionRepository permissionRepository,
        PermissionMapperService permissionMapperService
) implements PermissionService {

    @Override
    public List<PermissionDto> findAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapperService::convertToDto)
                .toList();
    }

    @Override
    public PermissionDto findPermissionById(UUID id) {
        return permissionMapperService.convertToDto(this.findById(id));
    }

    @Override
    public PermissionDto savePermission(PermissionCreateDto createDto) {
        return permissionMapperService.convertToDto(
                permissionRepository.save(
                        permissionMapperService.convertCreateDtoToEntity(createDto)
                ));
    }

    @Override
    public Permission createPermissionEntity(PermissionCreateDto createDto) {
        return permissionRepository.save(
                permissionMapperService.convertCreateDtoToEntity(createDto)
        );
    }

    @Override
    public void deletePermissionById(UUID id) {
        permissionRepository.delete(this.findById(id));
    }

    @Override
    public Permission findPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public void saveAllPermissions(List<Permission> permissions) {
        permissionRepository.saveAll(permissions);
    }

    private Permission findById(UUID id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "permission.not.found.by.name",
                        new String[]{String.valueOf(id)})
                );
    }
}