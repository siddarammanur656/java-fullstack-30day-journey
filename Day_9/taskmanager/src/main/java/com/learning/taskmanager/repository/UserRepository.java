package com.learning.taskmanager.repository;

import com.learning.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository — Day 11 concepts:
 *
 * 1. Derived query methods: Spring auto-generates SQL from method name
 * 2. @Query with JPQL: hand-written JPQL for complex needs
 * 3. LEFT JOIN FETCH: fixes N+1 by loading tasks in ONE query
 * 4. Aggregate projection: GROUP BY + COUNT without loading entities
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ── Derived Queries (Spring builds the SQL for you) ────────
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    List<User> findByActiveTrue();

    // ── JPQL: Fixes N+1 Problem ────────────────────────────────
    // Without this: 1 query to get user + N queries to get each task
    // With this:    1 query using JOIN FETCH loads user + tasks together
    @Query("SELECT u FROM User u " +
           "LEFT JOIN FETCH u.tasks " +
           "WHERE u.id = :id")
    Optional<User> findByIdWithTasks(@Param("id") Long id);

    // ── JPQL Projection: returns counts without loading all tasks ─
    // Day 11: aggregate query — GROUP BY + COUNT
    // Returns List<Object[]> → each row is [id, username, email, count]
    @Query("SELECT u.id, u.username, u.email, COUNT(t.id) as taskCount " +
           "FROM User u LEFT JOIN u.tasks t " +
           "WHERE u.active = true " +
           "GROUP BY u.id, u.username, u.email " +
           "ORDER BY taskCount DESC")
    List<Object[]> findUserTaskCounts();
}
