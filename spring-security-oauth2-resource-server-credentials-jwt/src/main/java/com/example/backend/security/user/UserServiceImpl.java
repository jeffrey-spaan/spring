package com.example.backend.security.user;

import com.example.backend.common.payload.exception.ResourceNotFoundException;
import com.example.backend.security.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserMapperService userMapperService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapperService::mapToDto)
                .toList();
    }

    @Override
    public UserDto findUserById(UUID id) {
        return userMapperService.mapToDto(this.findById(id));
    }

    @Override
    public List<UserMapDto> userCount() {
        return List.of(
                UserMapDto.builder()
                        .key("count")
                        .value(userRepository.count())
                        .build()
        );
    }

    @Override
    public List<UserMapDto> findAllUsersEnabled() {
        return List.of(
                UserMapDto.builder()
                        .key("active")
                        .value(userRepository.countByEnabledTrue())
                        .build(),
                UserMapDto.builder()
                        .key("inactive")
                        .value(userRepository.countByEnabledFalse())
                        .build()
        );
    }

    @Override
    public List<UserMapDto> userCountPerRole() {
        return roleRepository.findAll().stream()
                .map(role -> UserMapDto.builder()
                        .key(role.getName())
                        .value(userRepository.countByRoleId(role.getId()))
                        .build())
                .toList();
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        return userMapperService.mapToDto(this.saveUser(
                userMapperService.mapCreateDtoToEntity(userCreateDto))
        );
    }

    @Override
    public UserDto updateUserById(UUID id, UserUpdateDto userUpdateDto) {
        User user = this.findById(id);
        User updatedUser = userMapperService.mapUpdateDtoToEntity(user, userUpdateDto);
        return userMapperService.mapToDto(userRepository.save(updatedUser));
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.delete(this.findById(id));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found.by.id", new String[]{id.toString()}));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found.by.email", new String[]{email}));
    }

    @Override
    public User findByEmailOrUsername(String emailOrUsername) {
        return userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.by.email.or.username",
                        new String[]{emailOrUsername})
                );
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Async
    public void setUserEnabled(User user, boolean enabled) {
        user.setEnabled(enabled);
        userRepository.save(user);
    }
}