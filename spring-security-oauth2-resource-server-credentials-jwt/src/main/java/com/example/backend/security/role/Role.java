package com.example.backend.security.role;

import com.example.backend.common.constant.Constant;
import com.example.backend.common.constant.ValidationConstant;
import com.example.backend.security.permission.Permission;
import com.example.backend.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = Constant.Table.ROLES)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = ValidationConstant.Role.NAME_MAX_CHAR)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    private List<Permission> permissions;
}