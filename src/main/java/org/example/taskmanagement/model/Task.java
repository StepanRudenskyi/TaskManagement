package org.example.taskmanagement.model;


import jakarta.persistence.*;
import lombok.Data;
import org.example.taskmanagement.enums.TaskStatus;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "title")
    private String title;

    public Task() {

    }

    public Task(String description, TaskStatus status, String title) {
        this.description = description;
        this.status = status;
        this.title = title;
    }
}
