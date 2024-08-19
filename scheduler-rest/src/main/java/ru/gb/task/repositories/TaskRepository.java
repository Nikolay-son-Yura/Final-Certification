package ru.gb.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.task.model.Task;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUserId(Long ownerId);
}
