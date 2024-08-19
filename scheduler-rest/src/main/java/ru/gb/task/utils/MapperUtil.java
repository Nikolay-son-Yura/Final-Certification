package ru.gb.task.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.user.model.User;
import ru.gb.user.dto.UserDto;
import ru.gb.task.dto.TaskDto;
import ru.gb.task.model.Task;

public class MapperUtil {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertTOUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public TaskDto convertToTaskDto(Task task){
        return modelMapper.map(task, TaskDto.class);
    }
    public Task convertToTask(TaskDto taskDto){
        return modelMapper.map(taskDto,Task.class);
    }
}
