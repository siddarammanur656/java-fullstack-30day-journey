package com.learning.taskmanager.repository;

import com.learning.taskmanager.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * TaskRepository — updated for Day 11:
 *
 * Added Pageable methods for user-scoped task queries.
 * Day 11 concept: Pagination with Pageable avoids loading ALL tasks
 * from DB at once — critical for performance with large datasets.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // ── Legacy queries (kept for backward compatibility) ────────
    List<Task> findByStatus(Task.TaskStatus status);

    List<Task> findByTaskTitleContainingIgnoreCase(String keyword);

    List<Task> findByPriority(int priority);

    List<Task> findByStatusAndPriorityGreaterThanEqual(Task.TaskStatus status, int minPriority);

    List<Task> findAllByOrderByCreatedAtDesc();

    @Query("SELECT t FROM Task t WHERE t.priority >= :minP AND t.status != 'DONE' ORDER BY t.priority DESC")
    List<Task> findHighPriorityActiveTasks(@Param("minP") int minPriority);

    long countByStatus(Task.TaskStatus status);

    // ── New: User-scoped paginated queries (Day 11) ─────────────
    // Pageable = spring handles LIMIT, OFFSET, and ORDER BY for you
    Page<Task> findByUserId(Long userId, Pageable pageable);

    Page<Task> findByUserIdAndStatus(Long userId, Task.TaskStatus status, Pageable pageable);

    Page<Task> findByUserIdAndTaskTitleContainingIgnoreCase(Long userId, String keyword, Pageable pageable);

    // Single task for a user (ownership check)
    Optional<Task> findByIdAndUserId(Long id, Long userId);

    // Count tasks for a specific user (for stats)
    long countByUserId(Long userId);
}