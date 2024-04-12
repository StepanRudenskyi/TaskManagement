package org.example.taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDto {
    private Long id;
    private String name;
    private String email;
    private List<TaskDto> tasks;
}
