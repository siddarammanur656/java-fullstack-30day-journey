package com.learning.taskmanager.dto;

import jakarta.validation.constraints.*;

/**
 * UserRequestDTO — incoming data for creating/updating a user.
 * Day 11: Validation annotations ensure data integrity before DB.
 */
public class UserRequestDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",
             message = "Username may only contain letters, numbers, and underscores")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    // Getters
    public String getUsername() { return username; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setUsername(String u) { this.username = u; }
    public void setEmail(String e)    { this.email = e; }
    public void setPassword(String p) { this.password = p; }
}
