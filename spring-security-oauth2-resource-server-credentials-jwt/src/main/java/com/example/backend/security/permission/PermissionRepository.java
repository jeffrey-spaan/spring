package com.example.backend.security.permission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Permission findByName(String name);
}