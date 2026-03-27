package com.learning.taskmanager.controller;

import com.learning.taskmanager.dto.*;
import com.learning.taskmanager.model.Task;
import com.learning.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET /api/v1/tasks
    // GET /api/v1/tasks?keyword=spring
    // GET /api/v1/tasks?status=TODO
    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponseDTO>>>
    getAll(@RequestParam(required = false) String keyword,
           @RequestParam(required = false) Task.TaskStatus status) {

        List<Task> tasks = keyword != null ? taskService.search(keyword)
                         : status  != null ? taskService.getByStatus(status)
                         : taskService.getAll();

        return ResponseEntity.ok(
            ApiResponse.success(
                TaskResponseDTO.fromList(tasks),
                "Found " + tasks.size() + " task(s)"
            )
        );
    }

    // GET /api/v1/tasks/1
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDTO>>
    getById(@PathVariable Long id) {
        return ResponseEntity.ok(
            ApiResponse.success(
                TaskResponseDTO.from(taskService.getById(id)),
                "Task retrieved successfully"
            )
        );
    }

    // POST /api/v1/tasks
    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDTO>>
    create(@Valid @RequestBody TaskRequestDTO dto) {

        Task created = taskService.create(dto);
        URI location = URI.create("/api/v1/tasks/" + created.getId());

        return ResponseEntity
            .created(location)
            .body(ApiResponse.success(
                TaskResponseDTO.from(created),
                "Task created successfully"
            ));
    }

    // PUT /api/v1/tasks/1
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDTO>>
    update(@PathVariable Long id,
           @Valid @RequestBody TaskRequestDTO dto) {

        return ResponseEntity.ok(
            ApiResponse.success(
                TaskResponseDTO.from(taskService.update(id, dto)),
                "Task updated successfully"
            )
        );
    }

    // PATCH /api/v1/tasks/1/status
    // Body: {"status": "IN_PROGRESS"}
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<TaskResponseDTO>>
    updateStatus(@PathVariable Long id,
                 @RequestBody Map<String, String> body) {

        String statusStr = body.get("status");
        if (statusStr == null)
            throw new IllegalArgumentException(
                "Body must contain 'status' field");

        Task.TaskStatus newStatus;
        try {
            newStatus = Task.TaskStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Invalid status: " + statusStr +
                ". Valid values: TODO, IN_PROGRESS, DONE, CANCELLED");
        }

        return ResponseEntity.ok(
            ApiResponse.success(
                TaskResponseDTO.from(
                    taskService.updateStatus(id, newStatus)),
                "Status updated to " + newStatus
            )
        );
    }

    // DELETE /api/v1/tasks/1
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>
    delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok(
            ApiResponse.success("Task deleted successfully")
        );
    }

    // GET /api/v1/tasks/stats
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<Map<String, Long>>>
    getStats() {
        return ResponseEntity.ok(
            ApiResponse.success(
                taskService.getStats(),
                "Statistics retrieved"
            )
        );
    }
}