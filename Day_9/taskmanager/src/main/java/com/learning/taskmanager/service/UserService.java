package com.learning.taskmanager.service;

import com.learning.taskmanager.dto.*;
import com.learning.taskmanager.exception.*;
import com.learning.taskmanager.model.User;
import com.learning.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserService — CRUD + stats for users.
 *
 * Day 11 concepts:
 * - @Transactional(readOnly=true) for read operations (performance)
 * - DuplicateResourceException for unique constraint checks
 * - Projection query for aggregate task counts (avoids N+1)
 */
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ── CREATE ──────────────────────────────────────────────────
    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername()))
            throw new DuplicateResourceException(
                "User", "username", dto.getUsername());

        if (userRepository.existsByEmail(dto.getEmail()))
            throw new DuplicateResourceException(
                "User", "email", dto.getEmail());

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        // NOTE: Day 15 (Spring Security) will BCrypt-encode this
        user.setPassword(dto.getPassword());

        User saved = userRepository.save(user);
        System.out.println("✅ Created user ID: " + saved.getId());
        return UserResponseDTO.from(saved);
    }

    // ── READ ────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        return UserResponseDTO.from(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                new ResourceNotFoundException("User not found: " + username));
        return UserResponseDTO.from(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findByActiveTrue()
            .stream()
            .map(UserResponseDTO::from)
            .collect(Collectors.toList());
    }

    // ── UPDATE ──────────────────────────────────────────────────
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = findOrThrow(id);

        if (dto.getEmail() != null
                && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(dto.getEmail()))
                throw new DuplicateResourceException(
                    "User", "email", dto.getEmail());
            user.setEmail(dto.getEmail());
        }

        return UserResponseDTO.from(userRepository.save(user));
    }

    // ── SOFT DELETE ─────────────────────────────────────────────
    // We never hard-delete users — we just mark them as inactive.
    // This preserves audit history and avoids data loss.
    public void deactivateUser(Long id) {
        User user = findOrThrow(id);
        user.setActive(false);
        userRepository.save(user);
    }

    // ── STATS ───────────────────────────────────────────────────
    // Day 11: Uses aggregate JPQL projection — no N+1 problem here!
    // One query returns all user + taskCount data.
    @Transactional(readOnly = true)
    public List<UserTaskCountDTO> getUserTaskCounts() {
        return userRepository.findUserTaskCounts()
            .stream()
            .map(row -> new UserTaskCountDTO(
                (Long)   row[0],
                (String) row[1],
                (String) row[2],
                (Long)   row[3]
            ))
            .collect(Collectors.toList());
    }

    // ── Private Helper ──────────────────────────────────────────
    private User findOrThrow(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
}
