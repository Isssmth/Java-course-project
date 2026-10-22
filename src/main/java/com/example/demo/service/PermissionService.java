package com.example.demo.service;

import com.example.demo.entity.PermissionRequest;
import com.example.demo.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private final PermissionRepository repository;

    public PermissionService(PermissionRepository repository) {
        this.repository = repository;
    }

    public PermissionRequest createRequest(PermissionRequest request) {
        request.setStatus("PENDING");
        return repository.save(request);
    }

    public List<PermissionRequest> getAllRequests() {
        return repository.findAll();
    }

    public Optional<PermissionRequest> getRequestById(Long id) {
        return repository.findById(id);
    }

    public Optional<PermissionRequest> updateStatus(Long id, String status) {
        Optional<PermissionRequest> result = repository.findById(id);
        if (result.isPresent()) {
            PermissionRequest request = result.get();
            request.setStatus(status);
            return Optional.of(repository.save(request));
        }
        return Optional.empty();
    }
}
