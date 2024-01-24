package org.example.taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.taskmanagement.model.Task;

import java.util.List;

@Data
@AllArgsConstructor
public class DeveloperDto {
    private Long id;
    private String name;
    private String email;
    private List<TaskDto> tasks;

}
