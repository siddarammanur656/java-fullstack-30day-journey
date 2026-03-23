package com.learning.taskmanager.service;

import com.learning.taskmanager.model.Task;
import com.learning.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        boolean titleExists = taskRepository
            .findByTitleContainingIgnoreCase(task.getTitle())
            .stream()
            .anyMatch(t -> t.getTitle().equalsIgnoreCase(task.getTitle()));

        if (titleExists) {
            throw new IllegalArgumentException(
                "Task with title '" + task.getTitle() + "' already exists");
        }

        task.setStatus(Task.TaskStatus.TODO);
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Task not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Task> searchTasks(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllTasks();
        }
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        if (updatedTask.getTitle() != null)
            existingTask.setTitle(updatedTask.getTitle());

        if (updatedTask.getDescription() != null)
            existingTask.setDescription(updatedTask.getDescription());

        if (updatedTask.getPriority() > 0)
            existingTask.setPriority(updatedTask.getPriority());

        if (updatedTask.getDueDate() != null)
            existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    public Task updateStatus(Long id, Task.TaskStatus newStatus) {
        Task task = getTaskById(id);

        if (task.getStatus() == Task.TaskStatus.CANCELLED) {
            throw new IllegalStateException("Cannot change status of cancelled task");
        }

        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public TaskStats getStats() {
        long total       = taskRepository.count();
        long todo        = taskRepository.countByStatus(Task.TaskStatus.TODO);
        long inProgress  = taskRepository.countByStatus(Task.TaskStatus.IN_PROGRESS);
        long done        = taskRepository.countByStatus(Task.TaskStatus.DONE);
        long cancelled   = taskRepository.countByStatus(Task.TaskStatus.CANCELLED);
        return new TaskStats(total, todo, inProgress, done, cancelled);
    }

    public record TaskStats(long total, long todo,
                            long inProgress, long done, long cancelled) {}
}