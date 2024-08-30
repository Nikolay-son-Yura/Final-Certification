package ru.gb.task.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.task.dto.TaskDto;
import ru.gb.task.service.TaskService;
import ru.gb.task.utils.MapperUtil;
import ru.gb.user.dto.UserDto;
import ru.gb.user.model.User;
import ru.gb.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Планировщик задач", description = "Точка входа для работы с задачами пользователя")
//@RequestMapping("tasks")
@RestController
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


    @Operation(summary = "получение списка задач пользователя")
    @GetMapping("/task")
    public ResponseEntity<List<TaskDto>> getTasks(@RequestBody User user) {
        return ResponseEntity.ok(taskService.findByOwnerId(user.getId()).stream().map(mapperUtil::convertToTaskDto).collect(Collectors.toList()));
    }
//    public ResponseEntity<List<TaskDto>> getAllTasks(){
//        return ResponseEntity.ok(mapperUtil.convertToTaskDto(taskService.findAll()));
//    }

    @Operation(summary = "Поиск пользователя")
    @GetMapping("/user")
    public UserDto getUser(User user) {
        return mapperUtil.convertToUserDto(user);
    }

    @Operation(summary = "Создание задачи")
    @PostMapping(path = "/tasks")
    public ResponseEntity<HttpStatus> addTask(@RequestBody User user, @ModelAttribute TaskDto taskDto) {
        taskService.created(mapperUtil.convertToTask(taskDto), userService.findById(user.getId()).orElseThrow());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @Operation(summary = "Обновление задачи")
    @PatchMapping(path = "/tasks")
    public ResponseEntity<HttpStatus> updateTask(User user, TaskDto taskDto) {
        taskService.update(mapperUtil.convertToTask(taskDto), userService.findById(user.getId()).orElseThrow());
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping(path = "/tasks")
    public ResponseEntity<Void> deleteTasks(@ModelAttribute TaskDto taskDto) {
        taskService.delete(taskDto.getId());
        return ResponseEntity.noContent().build();
    }

}
