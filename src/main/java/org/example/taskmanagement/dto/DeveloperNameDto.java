package org.example.taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeveloperNameDto {
    private Long id;
    private String name;
    private Long activeTasks;
}
