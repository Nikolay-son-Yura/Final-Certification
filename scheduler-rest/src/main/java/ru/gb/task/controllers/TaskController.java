package ru.gb.task.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.security.TaskTrackerUserDetails;
import ru.gb.task.dto.TaskDto;
import ru.gb.task.service.TaskService;
import ru.gb.utils.MapperUtil;
import ru.gb.user.dto.UserDto;
import ru.gb.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Планировщик задач", description = "Точка входа для работы с задачами пользователя")
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


    @Operation(summary = "Поиск списка задач пользователя")
    @GetMapping("/tasks")
    public List<TaskDto> getTasks( TaskTrackerUserDetails taskTrackerUserDetails) {
        return taskService.findByOwnerId(taskTrackerUserDetails.getUser().getId()).stream()
                .map(mapperUtil::convertToTaskDto).collect(Collectors.toList());
    }


    @Operation(summary = "Поиск пользователя")
    @GetMapping("/user")
    public UserDto getUser(TaskTrackerUserDetails taskTrackerUserDetails) {
        return mapperUtil.convertToUserDto(taskTrackerUserDetails.getUser());
    }

    @Operation(summary = "Создание задачи")
    @PostMapping(path = "/tasks")
    public ResponseEntity<HttpStatus> addTask( TaskTrackerUserDetails taskTrackerUserDetails, @ModelAttribute TaskDto taskDto) {
        taskService.created(mapperUtil.convertToTask(taskDto), userService.findById(taskTrackerUserDetails.getUser().getId()).orElseThrow());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @Operation(summary = "Обновление задачи")
    @PatchMapping(path = "/tasks")
    public ResponseEntity<HttpStatus> updateTask( TaskTrackerUserDetails taskTrackerUserDetails, @ModelAttribute TaskDto taskDto) {
        taskService.update(mapperUtil.convertToTask(taskDto), userService.findById(taskTrackerUserDetails.getUser().getId()).orElseThrow());
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping(path = "/tasks")
    public ResponseEntity<Void> deleteTasks(@ModelAttribute TaskDto taskDto) {
        taskService.delete(taskDto.getId());
        return ResponseEntity.noContent().build();
    }

}
