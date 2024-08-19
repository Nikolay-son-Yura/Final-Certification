package ru.gb.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.user.model.User;
import ru.gb.task.model.Task;
import ru.gb.task.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List<Task> findByUserId(Long id) {
        return taskRepository.findByUserId(id);
    }

    public void created(Task task, User user) {
        task.setUser(user);
        task.setDateCreation(LocalDateTime.now());
        taskRepository.save(task);
    }
    public void update(Task task,User user){
        task.setUser(user);
        task.setDateCreation(LocalDateTime.now());
        taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
