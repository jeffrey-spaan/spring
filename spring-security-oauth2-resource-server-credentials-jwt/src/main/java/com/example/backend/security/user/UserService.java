package com.example.backend.security.user;

import java.util.List;
import java.util.UUID;

/**
 * @since 0.0.1
 * @see UserServiceImpl
 */
public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(UUID id);
    List<UserMapDto> userCount();
    List<UserMapDto> findAllUsersEnabled();
    List<UserMapDto> userCountPerRole();
    UserDto createUser(UserCreateDto userCreateDto);
    UserDto updateUserById(UUID id, UserUpdateDto userUpdateDto);
    void deleteUserById(UUID id);
    User findById(UUID id);
    User findByEmail(String email);
    User findByEmailOrUsername(String userNameOrEmail);
    User saveUser(User user);
    void setUserEnabled(User user, boolean enabled);
}