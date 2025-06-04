package com.example.backend.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.backend.common.constant.Constant;
import com.example.backend.common.constant.ValidationConstant;
import com.example.backend.security.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = Constant.Table.USERS)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = ValidationConstant.User.USERNAME_MAX_CHAR)
    private String username;

    @Column(nullable = false, unique = true, length = ValidationConstant.User.EMAIL_MAX_CHAR)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @Column(nullable = false, length = ValidationConstant.User.PASSWORD_MAX_CHAR)
    private String password;

    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .toList();
    }

    @Column(nullable = false)
    private boolean enabled;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}