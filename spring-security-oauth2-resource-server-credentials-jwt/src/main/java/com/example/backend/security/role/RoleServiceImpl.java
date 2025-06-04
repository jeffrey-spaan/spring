package com.example.backend.security.role;

import com.example.backend.common.payload.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapperService roleMapperService;
    private final RolePermissionServiceImpl rolePermissionServiceImpl;
    private final RoleRepository roleRepository;
    private final RoleValidationService roleValidationService;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("role.not.found.by.name", new String[]{name}));
    }

    @Override
    public List<RoleDto> findAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapperService::convertToDto)
                .toList();
    }

    @Override
    public RoleDto findRoleById(UUID id) {
        return roleMapperService.convertToDto(this.findById(id));
    }

    @Override
    public RoleDto createRole(RoleCreateDto roleCreateDto) {
        roleValidationService.validateRoleNameUniqueness(roleCreateDto.getName());
        Role role = roleMapperService.convertCreateRequestToEntity(roleCreateDto);

        role.setPermissions(rolePermissionServiceImpl.createPermissionsForRole(roleCreateDto));

        return roleMapperService.convertToDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public RoleDto updateRoleById(UUID id, RoleUpdateDto roleUpdateDto) {
        Role role = this.findById(id);
        roleMapperService.updateRoleFromDto(role, roleUpdateDto);
        return roleMapperService.convertToDto(roleRepository.save(role));
    }

    @Override
    public void deleteRoleById(UUID id) {
        roleRepository.delete(this.findById(id));
    }

    private Role findById(UUID id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "role.not.found.by.id",
                        new String[]{String.valueOf(id)})
                );
    }
}