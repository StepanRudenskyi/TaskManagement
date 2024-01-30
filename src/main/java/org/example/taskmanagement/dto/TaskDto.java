package org.example.taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.taskmanagement.enums.TaskStatus;

@Data
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String description;
    private TaskStatus status;
    private String title;
    public TaskDto() {

    }
}
