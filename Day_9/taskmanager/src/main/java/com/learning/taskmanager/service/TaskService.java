package com.learning.taskmanager.service;

import com.learning.taskmanager.dto.TaskRequestDTO;
import com.learning.taskmanager.exception.ResourceNotFoundException;
import com.learning.taskmanager.model.Task;
import com.learning.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //CREATE
    public Task create(TaskRequestDTO dto) {
        // Check duplicate title
        boolean exists = taskRepository
            .findByTitleContainingIgnoreCase(dto.getTitle())
            .stream()
            .anyMatch(t ->
                t.getTitle().equalsIgnoreCase(dto.getTitle()));

        if (exists) throw new IllegalArgumentException(
            "Task with title '" + dto.getTitle() + "' already exists");

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        task.setStatus(Task.TaskStatus.TODO);

        return taskRepository.save(task);
    }

    //READ
    @Transactional(readOnly = true)
    public List<Task> getAll() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Task", id));
    }

    @Transactional(readOnly = true)
    public List<Task> getByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Task> search(String keyword) {
        if (keyword == null || keyword.isBlank()) return getAll();
        return taskRepository
               .findByTitleContainingIgnoreCase(keyword);
    }

    // UPDATE
    public Task update(Long id, TaskRequestDTO dto) {
        Task task = getById(id);
        if (dto.getTitle()       != null) task.setTitle(dto.getTitle());
        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getPriority()    > 0)     task.setPriority(dto.getPriority());
        if (dto.getDueDate()     != null) task.setDueDate(dto.getDueDate());
        return taskRepository.save(task);
    }

    public Task updateStatus(Long id, Task.TaskStatus newStatus) {
        Task task = getById(id);
        if (task.getStatus() == Task.TaskStatus.CANCELLED)
            throw new IllegalStateException(
                "Cannot update a cancelled task");
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    // DELETE
    public void delete(Long id) {
        if (!taskRepository.existsById(id))
            throw new ResourceNotFoundException("Task", id);
        taskRepository.deleteById(id);
    }

    // STATS
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