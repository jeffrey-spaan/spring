package com.example.backend.security.user;

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
@RequestMapping(Constant.Request.USERS)
@RequiredArgsConstructor
public class  UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(
                userService.findAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        return new ResponseEntity<>(
                userService.findUserById(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    public ResponseEntity<List<UserMapDto>> getUserCount() {
        return new ResponseEntity<>(
                userService.userCount(),
                HttpStatus.OK
        );
    }

    @GetMapping("/enabled")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    public ResponseEntity<List<UserMapDto>> getAllUsersEnabled() {
        return new ResponseEntity<>(
                userService.findAllUsersEnabled(),
                HttpStatus.OK
        );
    }

    @GetMapping("/count/role")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_READ + "')")
    public ResponseEntity<List<UserMapDto>> getUserCountPerRole() {
        return new ResponseEntity<>(
                userService.userCountPerRole(),
                HttpStatus.OK
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_CREATE + "')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(
                userService.createUser(userCreateDto),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_UPDATE + "')")
    public ResponseEntity<UserDto> updateUserById(@PathVariable UUID id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return new ResponseEntity<>(
                userService.updateUserById(id, userUpdateDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Security.Scope.SCOPE_ADMIN_DELETE + "')")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}