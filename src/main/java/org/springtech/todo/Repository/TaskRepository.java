package org.springtech.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springtech.todo.Entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
