package com.learning.taskmanager.dto;

import com.learning.taskmanager.model.User;
import java.time.format.DateTimeFormatter;

/**
 * UserResponseDTO — outgoing user data. 
 * NEVER includes the password field (security best practice).
 *
 * Day 11: Static factory method pattern for clean DTO creation.
 * taskCount is populated safely — if tasks aren't loaded (LAZY),
 * it defaults to 0 rather than throwing LazyInitializationException.
 */
public class UserResponseDTO {

    private Long    id;
    private String  username;
    private String  email;
    private String  role;
    private boolean active;
    private String  createdAt;
    private int     taskCount;

    private UserResponseDTO() {}

    public static UserResponseDTO from(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.id       = user.getId();
        dto.username = user.getUsername();
        dto.email    = user.getEmail();
        dto.role     = user.getRole();
        dto.active   = user.isActive();
        dto.createdAt = user.getCreatedAt() != null
            ? user.getCreatedAt().format(
                DateTimeFormatter.ofPattern("dd MMM yyyy"))
            : null;

        // Day 11: Safely handle LAZY collection — if tasks weren't
        // eagerly fetched in this transaction, just return 0
        try {
            dto.taskCount = user.getTasks().size();
        } catch (Exception e) {
            dto.taskCount = 0;
        }
        return dto;
    }

    // Getters — no password getter!
    public Long    getId()        { return id; }
    public String  getUsername()  { return username; }
    public String  getEmail()     { return email; }
    public String  getRole()      { return role; }
    public boolean isActive()     { return active; }
    public String  getCreatedAt() { return createdAt; }
    public int     getTaskCount() { return taskCount; }
}
