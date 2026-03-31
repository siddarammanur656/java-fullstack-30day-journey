package com.learning.taskmanager.dto;

import com.learning.taskmanager.model.Task;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class TaskRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100,
          message = "Title must be between 2 and 100 characters")
    private String taskTitle;

    @Size(max = 1000,
          message = "Description cannot exceed 1000 characters")
    private String description;

    @Min(value = 1, message = "Priority must be at least 1")
    @Max(value = 5, message = "Priority cannot exceed 5")
    private int priority = 3;

    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    // Getters
    public String        getTaskTitle()   { return taskTitle; }
    public String        getDescription() { return description; }
    public int           getPriority()    { return priority; }
    public LocalDateTime getDueDate()     { return dueDate; }

    // Setters
    public void setTaskTitle(String t)       { this.taskTitle = t; }
    public void setDescription(String d)     { this.description = d; }
    public void setPriority(int p)           { this.priority = p; }
    public void setDueDate(LocalDateTime d)  { this.dueDate = d; }
}