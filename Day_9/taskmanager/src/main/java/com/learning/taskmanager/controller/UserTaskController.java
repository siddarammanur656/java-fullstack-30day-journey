package com.learning.taskmanager.controller;

import com.learning.taskmanager.dto.*;
import com.learning.taskmanager.exception.ResourceNotFoundException;
import com.learning.taskmanager.model.Task;
import com.learning.taskmanager.repository.UserRepository;
import com.learning.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * UserTaskController — user-scoped task endpoints.
 *
 * All tasks are accessed THROUGH their owning user:
 *   GET    /api/v1/users/{userId}/tasks           → paginated tasks
 *   POST   /api/v1/users/{userId}/tasks           → create task for user
 *   GET    /api/v1/users/{userId}/tasks/{taskId}  → single task (ownership check)
 *   DELETE /api/v1/users/{userId}/tasks/{taskId}  → delete task (ownership check)
 *
 * Day 11 feature highlight:
 * - Pagination: page, size, sortBy, dir params → Pageable
 * - Response includes: tasks + currentPage + totalItems + totalPages + isLast
 */
@RestController
@RequestMapping("/api/v1/users/{userId}/tasks")
public class UserTaskController {

    private final TaskService    taskService;
    private final UserRepository userRepository;

    public UserTaskController(TaskService taskService,
                              UserRepository userRepository) {
        this.taskService    = taskService;
        this.userRepository = userRepository;
    }

    // GET /api/v1/users/{userId}/tasks
    // Supports: ?page=0&size=10&sortBy=createdAt&dir=desc&status=TODO&keyword=spring
    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUserTasks(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0")         int page,
            @RequestParam(defaultValue = "10")        int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc")      String dir,
            @RequestParam(required = false) Task.TaskStatus status,
            @RequestParam(required = false) String keyword) {

        // Validate user exists before querying tasks
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", userId);

        Page<TaskResponseDTO> taskPage =
            taskService.getTasksPaged(userId, page, size, sortBy, dir, status, keyword);

        // Build paginated response body
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("tasks",       taskPage.getContent());
        body.put("currentPage", taskPage.getNumber());
        body.put("totalItems",  taskPage.getTotalElements());
        body.put("totalPages",  taskPage.getTotalPages());
        body.put("isLast",      taskPage.isLast());

        return ResponseEntity.ok(
            ApiResponse.success(body, "Tasks retrieved successfully"));
    }

    // POST /api/v1/users/{userId}/tasks
    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDTO>> createTask(
            @PathVariable Long userId,
            @Valid @RequestBody TaskRequestDTO dto) {

        Task created = taskService.createForUser(userId, dto);
        URI location = URI.create(
            "/api/v1/users/" + userId + "/tasks/" + created.getId());

        return ResponseEntity
            .created(location)
            .body(ApiResponse.success(
                TaskResponseDTO.from(created),
                "Task created successfully"
            ));
    }

    // GET /api/v1/users/{userId}/tasks/{taskId}
    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskResponseDTO>> getTask(
            @PathVariable Long userId,
            @PathVariable Long taskId) {

        Task task = taskService.getTaskForUser(userId, taskId);
        return ResponseEntity.ok(
            ApiResponse.success(
                TaskResponseDTO.from(task), "Task retrieved successfully"));
    }

    // DELETE /api/v1/users/{userId}/tasks/{taskId}
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(
            @PathVariable Long userId,
            @PathVariable Long taskId) {

        taskService.deleteForUser(userId, taskId);
        return ResponseEntity.ok(
            ApiResponse.success("Task deleted successfully"));
    }
}
