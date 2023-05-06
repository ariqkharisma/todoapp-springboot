package com.ariq.todo.task;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByPriority(String priority);
    List<Task> findByIsDone(boolean isDone);
}
