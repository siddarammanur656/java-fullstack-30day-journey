package com.learning.taskmanager.dto;

import com.learning.taskmanager.model.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskResponseDTO — updated for Day 11 to include owner information.
 * Now exposes userId and username so consumers know who owns the task.
 */
public class TaskResponseDTO {

    private Long    id;
    private String  taskTitle;
    private String  description;
    private String  status;
    private int     priority;
    private String  priorityLabel;
    private String  createdAt;
    private String  dueDate;
    private boolean overdue;

    // New in Day 11: owner information
    private Long   userId;
    private String username;

    private TaskResponseDTO() {}

    public static TaskResponseDTO from(Task task) {
        TaskResponseDTO dto  = new TaskResponseDTO();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        dto.id          = task.getId();
        dto.taskTitle   = task.getTaskTitle();
        dto.description = task.getDescription();
        dto.status      = task.getStatus().name();
        dto.priority    = task.getPriority();
        dto.priorityLabel = switch (task.getPriority()) {
            case 5  -> "Critical";
            case 4  -> "High";
            case 3  -> "Medium";
            case 2  -> "Low";
            default -> "Minimal";
        };
        dto.createdAt = task.getCreatedAt() != null
            ? task.getCreatedAt().format(fmt) : null;
        dto.dueDate = task.getDueDate() != null
            ? task.getDueDate().format(fmt) : null;
        dto.overdue = task.getDueDate() != null
            && LocalDateTime.now().isAfter(task.getDueDate())
            && task.getStatus() != Task.TaskStatus.DONE
            && task.getStatus() != Task.TaskStatus.CANCELLED;

        // Safely populate user info (may be null if not loaded)
        if (task.getUser() != null) {
            dto.userId   = task.getUser().getId();
            dto.username = task.getUser().getUsername();
        }
        return dto;
    }

    public static List<TaskResponseDTO> fromList(List<Task> tasks) {
        return tasks.stream()
                    .map(TaskResponseDTO::from)
                    .collect(Collectors.toList());
    }

    // Getters
    public Long    getId()            { return id; }
    public String  getTaskTitle()     { return taskTitle; }
    public String  getDescription()   { return description; }
    public String  getStatus()        { return status; }
    public int     getPriority()      { return priority; }
    public String  getPriorityLabel() { return priorityLabel; }
    public String  getCreatedAt()     { return createdAt; }
    public String  getDueDate()       { return dueDate; }
    public boolean isOverdue()        { return overdue; }
    public Long    getUserId()        { return userId; }
    public String  getUsername()      { return username; }
}