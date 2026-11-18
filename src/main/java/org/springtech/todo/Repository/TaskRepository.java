package org.springtech.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springtech.todo.Entities.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);
    Optional<Task> findByTitle(String title);
}
