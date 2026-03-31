package com.learning.taskmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity — owns tasks (One-to-Many relationship).
 * Demonstrates: @OneToMany with LAZY fetching, @PrePersist lifecycle hook.
 *
 * Day 11 concept: Entity relationships and lazy loading.
 * Tasks are LAZY-loaded → only fetched when explicitly accessed,
 * avoiding the N+1 problem when just listing users.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String role = "USER";

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ── One-to-Many relationship ────────────────────────────────
    // LAZY = tasks are NOT loaded unless you call gettasks()
    // This FIXES N+1: listing users never fetches all their tasks
    // mappedBy = the field name in Task that owns the FK
    // cascade ALL = save/delete user cascades to tasks
    // orphanRemoval = removing a task from the list deletes it from DB
    @OneToMany(
        mappedBy    = "user",
        cascade     = CascadeType.ALL,
        fetch       = FetchType.LAZY,
        orphanRemoval = true
    )
    private List<Task> tasks = new ArrayList<>();

    // ── Lifecycle Callbacks ────────────────────────────────────
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ── Helper methods for bidirectional relationship ───────────
    public void addTask(Task task) {
        tasks.add(task);
        task.setUser(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setUser(null);
    }

    // ── Getters ────────────────────────────────────────────────
    public Long          getId()        { return id; }
    public String        getUsername()  { return username; }
    public String        getEmail()     { return email; }
    public String        getPassword()  { return password; }
    public String        getRole()      { return role; }
    public boolean       isActive()     { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Task>    getTasks()     { return tasks; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id)             { this.id = id; }
    public void setUsername(String u)      { this.username = u; }
    public void setEmail(String e)         { this.email = e; }
    public void setPassword(String p)      { this.password = p; }
    public void setRole(String r)          { this.role = r; }
    public void setActive(boolean a)       { this.active = a; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}
