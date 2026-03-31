package com.learning.taskmanager.service;

import com.learning.taskmanager.dto.TaskRequestDTO;
import com.learning.taskmanager.dto.TaskResponseDTO;
import com.learning.taskmanager.exception.ResourceNotFoundException;
import com.learning.taskmanager.model.Task;
import com.learning.taskmanager.model.User;
import com.learning.taskmanager.repository.TaskRepository;
import com.learning.taskmanager.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * TaskService — updated for Day 11.
 *
 * New features:
 * - createForUser: creates task owned by a specific user
 * - getTasksPaged: paginated + filtered tasks for a user
 *   (Day 11: Pageable avoids loading ALL tasks — critical for scale)
 * - getTaskForUser / deleteForUser: ownership-validated operations
 */
@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // ── CREATE (user-scoped) ─────────────────────────────────────
    public Task createForUser(Long userId, TaskRequestDTO dto) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        Task task = new Task();
        task.setTaskTitle(dto.getTaskTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        task.setStatus(Task.TaskStatus.TODO);
        task.setUser(user);  // ← establishes the relationship

        return taskRepository.save(task);
    }

    // ── READ (paginated, user-scoped) ────────────────────────────
    // Day 11: PageRequest builds the Pageable with sort + direction
    // Spring Data then translates it to: SELECT ... LIMIT x OFFSET y
    @Transactional(readOnly = true)
    public Page<TaskResponseDTO> getTasksPaged(
            Long userId, int page, int size,
            String sortBy, String dir,
            Task.TaskStatus status, String keyword) {

        Sort sort = dir.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Task> taskPage;
        if (keyword != null && !keyword.isBlank()) {
            taskPage = taskRepository
                .findByUserIdAndTaskTitleContainingIgnoreCase(
                    userId, keyword, pageable);
        } else if (status != null) {
            taskPage = taskRepository
                .findByUserIdAndStatus(userId, status, pageable);
        } else {
            taskPage = taskRepository.findByUserId(userId, pageable);
        }

        // Map Task → TaskResponseDTO, keeping pagination metadata
        return taskPage.map(TaskResponseDTO::from);
    }

    // ── READ (single, ownership-validated) ──────────────────────
    @Transactional(readOnly = true)
    public Task getTaskForUser(Long userId, Long taskId) {
        return taskRepository.findByIdAndUserId(taskId, userId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Task " + taskId + " not found for user " + userId));
    }

    // ── DELETE (ownership-validated) ────────────────────────────
    public void deleteForUser(Long userId, Long taskId) {
        Task task = getTaskForUser(userId, taskId);
        taskRepository.delete(task);
    }

    // ── Legacy methods (backward compat with TaskController) ─────
    public Task create(TaskRequestDTO dto) {
        throw new UnsupportedOperationException(
            "Use POST /api/v1/users/{userId}/tasks instead");
    }

    @Transactional(readOnly = true)
    public List<Task> getAll() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task", id));
    }

    @Transactional(readOnly = true)
    public List<Task> getByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Task> search(String keyword) {
        if (keyword == null || keyword.isBlank()) return getAll();
        return taskRepository.findByTaskTitleContainingIgnoreCase(keyword);
    }

    public Task update(Long id, TaskRequestDTO dto) {
        Task task = getById(id);
        if (dto.getTaskTitle()   != null) task.setTaskTitle(dto.getTaskTitle());
        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getPriority()    > 0)     task.setPriority(dto.getPriority());
        if (dto.getDueDate()     != null) task.setDueDate(dto.getDueDate());
        return taskRepository.save(task);
    }

    public Task updateStatus(Long id, Task.TaskStatus newStatus) {
        Task task = getById(id);
        if (task.getStatus() == Task.TaskStatus.CANCELLED)
            throw new IllegalStateException("Cannot update a cancelled task");
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id))
            throw new ResourceNotFoundException("Task", id);
        taskRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStats() {
        return Map.of(
            "total",      taskRepository.count(),
            "todo",       taskRepository.countByStatus(Task.TaskStatus.TODO),
            "inProgress", taskRepository.countByStatus(Task.TaskStatus.IN_PROGRESS),
            "done",       taskRepository.countByStatus(Task.TaskStatus.DONE),
            "cancelled",  taskRepository.countByStatus(Task.TaskStatus.CANCELLED)
        );
    }
}