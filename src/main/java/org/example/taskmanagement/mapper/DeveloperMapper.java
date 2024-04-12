package org.example.taskmanagement.mapper;

import org.example.taskmanagement.dto.DeveloperDto;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    Developer toEntity(DeveloperDto developerDto);
    DeveloperDto toDto (Developer developer);
}
