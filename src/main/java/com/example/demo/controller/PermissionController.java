package com.example.demo.controller;

import com.example.demo.entity.PermissionRequest;
import com.example.demo.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PermissionRequest> createRequest(@RequestBody PermissionRequest request) {
        PermissionRequest saved = service.createRequest(request);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<PermissionRequest> getAllRequests() {
        return service.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionRequest> getRequestById(@PathVariable Long id) {
        return service.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestBody Map<String, String> body) {
        String status = body.get("status");
        if (status == null ||
                !(status.equals("APPROVED") || status.equals("REJECTED") || status.equals("PENDING"))) {
            return ResponseEntity.badRequest()
                    .body("Invalid status. Use PENDING, APPROVED, or REJECTED.");
        }
        return service.updateStatus(id, status)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
