package com.learning.taskmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Task entity — belongs to a User (Many-to-One relationship).
 *
 * Day 11 concepts demonstrated:
 * - @ManyToOne with LAZY fetch (each task has one owner)
 * - @JoinColumn defines the FK column "user_id"
 * - LAZY fetch = user is NOT loaded unless you call getUser(),
 *   which prevents unnecessary joins when only task data is needed
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column name matches V2 migration: task_title
    @Column(name = "task_title", nullable = false, length = 100)
    private String taskTitle;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TODO;

    @Column(nullable = false)
    private int priority = 3;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime dueDate;

    // ── Many-to-One: many tasks belong to one user ─────────────
    // LAZY = user data is only loaded when explicitly needed
    // nullable=false = every task MUST have an owner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ── Lifecycle Callbacks ────────────────────────────────────
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ── Enum ───────────────────────────────────────────────────
    public enum TaskStatus { TODO, IN_PROGRESS, DONE, CANCELLED }

    // ── Getters ────────────────────────────────────────────────
    public Long          getId()          { return id; }
    public String        getTaskTitle()   { return taskTitle; }
    public String        getDescription() { return description; }
    public TaskStatus    getStatus()      { return status; }
    public int           getPriority()    { return priority; }
    public LocalDateTime getCreatedAt()   { return createdAt; }
    public LocalDateTime getUpdatedAt()   { return updatedAt; }
    public LocalDateTime getDueDate()     { return dueDate; }
    public User          getUser()        { return user; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id)                { this.id = id; }
    public void setTaskTitle(String t)        { this.taskTitle = t; }
    public void setDescription(String d)      { this.description = d; }
    public void setStatus(TaskStatus s)       { this.status = s; }
    public void setPriority(int p)            { this.priority = p; }
    public void setDueDate(LocalDateTime d)   { this.dueDate = d; }
    public void setUser(User u)               { this.user = u; }
}