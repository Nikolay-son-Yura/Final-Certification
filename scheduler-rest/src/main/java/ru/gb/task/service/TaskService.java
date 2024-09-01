package ru.gb.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.user.model.User;
import ru.gb.task.model.Task;
import ru.gb.task.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
//    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
//        this.userRepository = userRepository;
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Задача не существует"));
    }
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List<Task> findByOwnerId(Long id) {
        return taskRepository.findByOwnerId(id);
    }

    public void created(Task task, User user) {
        task.setOwner(user);
        task.setDateCreation(LocalDateTime.now());
        taskRepository.save(task);
    }
    public void update(Task task,User user){
        task.setOwner(user);
        task.setDateCreation(LocalDateTime.now());
        taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
