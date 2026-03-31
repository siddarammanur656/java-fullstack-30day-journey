package com.learning.taskmanager.controller;

import com.learning.taskmanager.dto.*;
import com.learning.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * UserController — REST API for user management.
 *
 * Endpoints:
 *   GET    /api/v1/users                    → list all active users
 *   GET    /api/v1/users/{id}               → get user by id
 *   POST   /api/v1/users                    → create user (201 Created)
 *   PUT    /api/v1/users/{id}               → update user email
 *   DELETE /api/v1/users/{id}               → soft-deactivate user
 *   GET    /api/v1/users/stats/task-counts  → aggregate task stats
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/v1/users
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAll() {
        return ResponseEntity.ok(
            ApiResponse.success(
                userService.getAllUsers(),
                "Users retrieved successfully"
            )
        );
    }

    // GET /api/v1/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            ApiResponse.success(
                userService.getUserById(id),
                "User retrieved successfully"
            )
        );
    }

    // POST /api/v1/users
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> create(
            @Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        URI location = URI.create("/api/v1/users/" + created.getId());
        return ResponseEntity
            .created(location)
            .body(ApiResponse.success(created, "User created successfully"));
    }

    // PUT /api/v1/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(
            ApiResponse.success(
                userService.updateUser(id, dto),
                "User updated successfully"
            )
        );
    }

    // DELETE /api/v1/users/{id}  (soft delete — sets active=false)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivate(
            @PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok(
            ApiResponse.success("User deactivated successfully")
        );
    }

    // GET /api/v1/users/stats/task-counts
    @GetMapping("/stats/task-counts")
    public ResponseEntity<ApiResponse<List<UserTaskCountDTO>>> getTaskCounts() {
        return ResponseEntity.ok(
            ApiResponse.success(
                userService.getUserTaskCounts(),
                "Task counts retrieved successfully"
            )
        );
    }
}
