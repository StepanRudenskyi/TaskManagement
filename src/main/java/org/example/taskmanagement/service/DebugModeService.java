package org.example.taskmanagement.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.taskmanagement.configuration.FlagProperties;
import org.example.taskmanagement.enums.TaskStatus;
import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.repository.DeveloperRepository;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DebugModeService {
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private FlagProperties flagProperties;


    @PostConstruct
    @Transactional
    public void createTestData() {
        if (flagProperties.isDebugModeEnabled()) {
            // create developers
            Developer johnDoe = new Developer("John Doe", "john.doe@example.com", null);
            Developer janeSmith = new Developer("Jane Smith", "jane.smith@example.com", null);
            developerRepository.saveAll(List.of(johnDoe, janeSmith));

            // create tasks
            Task frontendDesign = new Task("Frontend Design", TaskStatus.IN_PROGRESS, "Design the user interface for the application");
            Task backendImplementation = new Task("Backend Implementation", TaskStatus.NOT_STARTED, "Implement backend functionality");
            Task userAuthentication = new Task("User Authentication", TaskStatus.NOT_STARTED, "Implement user authentication");
            Task databaseDesign = new Task("Database Schema Design", TaskStatus.IN_PROGRESS, "Design database schema");

            // associate tasks with developers
            frontendDesign.setDeveloper(johnDoe);
            backendImplementation.setDeveloper(janeSmith);
            taskRepository.saveAll(List.of(frontendDesign, backendImplementation, userAuthentication, databaseDesign));
        }
    }

    @PreDestroy
    @Transactional
    public void deleteTestData() {
        if (flagProperties.isDebugModeEnabled()) {
            developerRepository.deleteAll();
            taskRepository.deleteAll();
        }
    }

}
