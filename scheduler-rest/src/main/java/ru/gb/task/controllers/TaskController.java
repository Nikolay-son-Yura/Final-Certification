package ru.gb.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.task.dto.TaskDto;
import ru.gb.task.model.Task;
import ru.gb.task.service.TaskService;
import ru.gb.task.utils.MapperUtil;
import ru.gb.user.service.UserService;

import java.util.List;

public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final MapperUtil mapperUtil;

    @Autowired
    public TaskController(TaskService taskService, UserService userService, MapperUtil mapperUtil) {
        this.taskService = taskService;
        this.userService = userService;
        this.mapperUtil = mapperUtil;
    }
    public ResponseEntity<Task> addTask(@RequestBody Task task){

//        taskService.created(mapperUtil.convertToTask(taskDto),userService.saveUser());
        return  null;
    }
}
