package org.example.taskmanagement;

import java.util.List;

import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.repository.DeveloperRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeveloperServiceIT {


    @Autowired
    DeveloperRepository developerRepository;
    
    @BeforeEach
    public void testSetup() {
    	
    	/*
    	// create developers
        Developer johnDoe = new Developer("John Doe", "john.doe@example.com", null);
        Developer janeSmith = new Developer("Jane Smith", "jane.smith@example.com", null);
        // create tasks
        Task frontendDesign = new Task("Frontend Design", TaskStatus.IN_PROGRESS, "Design the user interface for the application");
        Task backendImplementation = new Task("Backend Implementation", TaskStatus.NOT_STARTED, "Implement backend functionality");
        Task userAuthentication = new Task("User Authentication", TaskStatus.NOT_STARTED, "Implement user authentication");
        Task databaseDesign = new Task("Database Schema Design", TaskStatus.IN_PROGRESS, "Design database schema");
        
        //assign tasks
        johnDoe.setTasks(List.of(frontendDesign));
        janeSmith.setTasks(List.of(databaseDesign));
        
        //persist devs
        developerRepository.saveAll(java.util.Arrays.asList(johnDoe, janeSmith));
        */

        
    	
    	
    }

    @Test
    void testFindAllDevelopersWithActiveTasksCount() throws Exception {
    	
    	List<Developer> devsWithTasks =  developerRepository.findAllDevelopersWithActiveTasksCount();
    	Assertions.assertEquals("john.doe@example.com", devsWithTasks.get(0).getEmail());
        
    }

}