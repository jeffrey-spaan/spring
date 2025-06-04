package com.example.backend.security.user;

import com.example.backend.security.role.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
record UserMapperService(
        PasswordEncoder passwordEncoder,
        RoleService roleService
) {

    public UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getName()
            );
    }

    public User mapCreateDtoToEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.username());
        user.setEmail(userCreateDto.email());
        user.setPassword(passwordEncoder.encode(userCreateDto.password()));
        user.setRole(roleService.findRoleByName(userCreateDto.role()));
        return user;
    }

    public User mapUpdateDtoToEntity(User user, UserUpdateDto userUpdateDto) {
        user.setUsername(userUpdateDto.username());
        user.setEmail(userUpdateDto.email());
        user.setPassword(passwordEncoder.encode(userUpdateDto.password()));
        user.setRole(roleService.findRoleByName(userUpdateDto.role()));
        return user;
    }

    public Map<String, Long> mapUsersByRole(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(
                        user -> user.getRole().getName(),
                        Collectors.counting()
                ));
    }
}