package com.example.demo.repository;

import com.example.demo.entity.PermissionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionRequest, Long> {
}
