package org.example.taskmanagement.controller;

import org.example.taskmanagement.dto.DeveloperDto;
import org.example.taskmanagement.dto.DeveloperNameDto;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    /**
     * RESTful endpoint to create a new developer.
     * <p>
     * This endpoint allows clients to create a new developer by providing the necessary information
     * in the request body. The information should be in the form of a JSON object. The created developer
     * is returned as a response in the form of a {@link DeveloperDto}.
     *
     * @param developerDto The developer details provided in the request body.
     * @return A {@link DeveloperDto} represent enw created developer.
     */
    @PostMapping
    public DeveloperDto createDeveloper(@RequestBody DeveloperDto developerDto) {
        return developerService.createDeveloper(developerDto);
    }

    /**
     * RESTful endpoint to retrieve a developer by their unique identifier.
     * <p>
     * This endpoint allows clients to retrieve details of a specific developer by providing
     * the developer's ID as a path variable. If the developer with the specified ID exists,
     * their details are returned as a response in the form of a {@link DeveloperDto}.
     *
     * @param developerId The unique identifier of the developer to be retrieved.
     * @return A {@link DeveloperDto} represent the details of the requested developer
     * @throws jakarta.persistence.EntityNotFoundException if developer with provided developerId was not found
     */
    @GetMapping("/{developerId}")
    public DeveloperDto getDeveloperById(@PathVariable Long developerId) {
        return developerService.getDeveloperById(developerId);
    }

    @PutMapping("/{developerId}")
    public void updateDeveloper(@PathVariable Long developerId, @RequestBody DeveloperDto updatedDeveloperDto) {
        developerService.updateDeveloper(developerId, updatedDeveloperDto);
    }

    @DeleteMapping("/{developerId}")
    public void deleteDeveloper(@PathVariable Long developerId) {
        developerService.deleteDeveloper(developerId);
    }

    @PostMapping("/{developerId}/tasks")
    public void associateTasksWithDeveloper(@PathVariable Long developerId,
                                            @RequestBody List<TaskDto> taskDtos) {
        developerService.associateTasksWithDeveloper(developerId, taskDtos);
    }

    @GetMapping("/{developerId}/tasks")
    public List<TaskDto> getTasksByDeveloperId(@PathVariable Long developerId) {
        return developerService.getTaskByDeveloperId(developerId);
    }

    @GetMapping("/active-tasks")
    public List<DeveloperNameDto> getAllDevelopersWithActiveTasks() {
        return developerService.getAllDevelopersWithActiveTasks();
    }
    @GetMapping("/active-task-count")
    public List<DeveloperNameDto> getAllDevelopersWithActiveTasksCount() {
        return developerService.getAllDevelopersWithActiveTasksCount();
    }

}
