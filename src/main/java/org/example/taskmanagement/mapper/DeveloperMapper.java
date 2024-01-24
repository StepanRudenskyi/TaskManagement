package org.example.taskmanagement.mapper;

import org.example.taskmanagement.dto.DeveloperDto;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    public Developer toEntity(DeveloperDto developerDto);
    public DeveloperDto toDto (Developer developer);
    TaskDto toTaskDto(Task task);

}
