package com.learning.taskmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TODO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    private int priority = 3;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum TaskStatus { TODO, IN_PROGRESS, DONE, CANCELLED }

    // Getters and setters
    public Long          getId()          { return id; }
    public String        getTitle()       { return title; }
    public String        getDescription() { return description; }
    public TaskStatus    getStatus()      { return status; }
    public LocalDateTime getCreatedAt()   { return createdAt; }
    public LocalDateTime getUpdatedAt()   { return updatedAt; }
    public LocalDateTime getDueDate()     { return dueDate; }
    public int           getPriority()    { return priority; }

    public void setId(Long id)                { this.id = id; }
    public void setTitle(String t)            { this.title = t; }
    public void setDescription(String d)      { this.description = d; }
    public void setStatus(TaskStatus s)       { this.status = s; }
    public void setDueDate(LocalDateTime d)   { this.dueDate = d; }
    public void setPriority(int p)            { this.priority = p; }
}