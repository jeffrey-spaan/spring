package com.example.backend.security.auth;

import com.example.backend.common.constant.Constant;
import com.example.backend.security.role.RoleService;
import com.example.backend.security.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
record AuthMapperService(
        PasswordEncoder passwordEncoder,
        RoleService roleService
) {

    public User mapCreateDtoToEntity(AuthRegisterDto authRegisterDto) {
        User user = new User();
        user.setUsername(authRegisterDto.getUsername());
        user.setEmail(authRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(authRegisterDto.getPassword()));
        user.setRole(roleService.findRoleByName(Constant.Security.Role.ROLE_DEFAULT));
        return user;
    }
}