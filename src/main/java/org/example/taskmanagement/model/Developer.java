package org.example.taskmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.REMOVE, orphanRemoval = false)
    private List<Task> tasks;

    public Developer(String name, String email, List<Task> tasks) {
        this.name = name;
        this.email = email;
        this.tasks = tasks;
    }

    public Developer() {

    }
}
