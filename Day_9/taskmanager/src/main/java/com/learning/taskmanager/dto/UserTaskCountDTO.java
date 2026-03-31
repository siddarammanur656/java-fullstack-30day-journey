package com.learning.taskmanager.dto;

/**
 * UserTaskCountDTO — projection DTO for the aggregate JPQL query.
 *
 * Day 11: Instead of loading full User + Task entities, we use
 * a lightweight DTO to carry only what the API consumer needs.
 * This is the "projection" pattern for efficient data retrieval.
 */
public class UserTaskCountDTO {

    private Long   userId;
    private String username;
    private String email;
    private Long   taskCount;

    public UserTaskCountDTO(Long userId, String username,
                            String email, Long taskCount) {
        this.userId    = userId;
        this.username  = username;
        this.email     = email;
        this.taskCount = taskCount;
    }

    // Getters
    public Long   getUserId()    { return userId; }
    public String getUsername()  { return username; }
    public String getEmail()     { return email; }
    public Long   getTaskCount() { return taskCount; }
}
