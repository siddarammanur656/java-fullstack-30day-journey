package com.learning.taskmanager.repository;

import com.learning.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Task.TaskStatus status);

    List<Task> findByTitleContainingIgnoreCase(String keyword);

    List<Task> findByPriority(int priority);

    List<Task> findByStatusAndPriorityGreaterThanEqual(Task.TaskStatus status, int minPriority);

    List<Task> findAllByOrderByCreatedAtDesc();

    @Query("SELECT t FROM Task t WHERE t.priority >= :minP AND t.status != 'DONE' ORDER BY t.priority DESC")
    List<Task> findHighPriorityActiveTasks(@Param("minP") int minPriority);

    long countByStatus(Task.TaskStatus status);
}