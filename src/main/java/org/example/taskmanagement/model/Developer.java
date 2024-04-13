package org.example.taskmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    // TODO: implement cascading delete
    @OneToMany(mappedBy = "developer", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<Task> tasks;

    public Developer(String name, String email, List<Task> tasks) {
        this.name = name;
        this.email = email;
        this.tasks = tasks;
    }
}
