package org.example.taskmanagement.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.taskmanagement.enums.TaskStatus;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    public Task(String description, TaskStatus status, String title) {
        this.description = description;
        this.status = status;
        this.title = title;
    }
}
