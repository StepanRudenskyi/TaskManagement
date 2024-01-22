package org.example.taskmanagement.mapper;

import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    public Task toEntity(TaskDto taskDto);
    public TaskDto toDto(Task task);

}
