package org.example.taskmanagement.mapper;

import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskDto taskDto);
    TaskDto toDto(Task task);
}
