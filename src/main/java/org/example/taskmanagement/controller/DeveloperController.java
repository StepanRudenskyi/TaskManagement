package org.example.taskmanagement.controller;

import jakarta.persistence.EntityNotFoundException;
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
     * @throws EntityNotFoundException if developer with provided developerId was not found
     */
    @GetMapping("/{developerId}")
    public DeveloperDto getDeveloperById(@PathVariable Long developerId) {
        return developerService.getDeveloperById(developerId);
    }

    /**
     * RESTful endpoint to update an existing developer.
     * <p>
     * This endpoint allows clients to update an existing developer by providing the updated
     * details in the request body. The developerId in the path variable specifies the developer
     * to be updated.
     *
     * @param developerId The unique identifier of the developer to be updated.
     * @param updatedDeveloperDto The updated details of the developer provided in the request body.
     */
    @PutMapping("/{developerId}")
    public void updateDeveloper(@PathVariable Long developerId, @RequestBody DeveloperDto updatedDeveloperDto) {
        developerService.updateDeveloper(developerId, updatedDeveloperDto);
    }

    /**
     * RESTful endpoint to delete a developer by their unique identifier.
     * <p>
     * This endpoint allows clients to delete a specific developer by providing the developer's ID
     * as a path variable. If the developer with the specified ID exists, they will be deleted.
     *
     * @param developerId The unique identifier of the developer to be deleted.
     * @throws EntityNotFoundException if the developer with the provided developerId was not found.
     */
    @DeleteMapping("/{developerId}")
    public void deleteDeveloper(@PathVariable Long developerId) {
        developerService.deleteDeveloper(developerId);
    }

    /**
     * RESTful endpoint to retrieve tasks associated with a developer.
     * <p>
     * This endpoint allows clients to retrieve the tasks associated with a specific developer
     * by providing the developer's ID as a path variable. If the developer with the specified ID
     * exists, their associated tasks are returned as a response in the form of a list of {@link TaskDto}.
     *
     * @param developerId The unique identifier of the developer for whom tasks are to be retrieved.
     * @return A list of {@link TaskDto} representing the tasks associated with the specified developer.
     * @throws EntityNotFoundException if the developer with the provided developerId was not found.
     */
    @GetMapping("/{developerId}/tasks")
    public List<TaskDto> getTasksByDeveloperId(@PathVariable Long developerId) {
        return developerService.getTaskByDeveloperId(developerId);
    }

    /**
     * RESTful endpoint to retrieve all developers with active tasks.
     * <p>
     * This endpoint allows clients to retrieve a list of developers who have active tasks. Each developer
     * is represented with their name and the count of their active tasks in the form of a {@link DeveloperNameDto}.
     *
     * @return A list of {@link DeveloperNameDto} representing developers with active tasks and their task counts.
     */
    @GetMapping("/active-tasks")
    public List<DeveloperNameDto> getAllDevelopersWithActiveTasks() {
        return developerService.getAllDevelopersWithActiveTasks();
    }
    /**
     * RESTful endpoint to retrieve all developers with the count of their active tasks.
     * <p>
     * This endpoint allows clients to retrieve a list of developers along with the count of their active tasks.
     * Each developer is represented with their name and the count of their active tasks in the form of a {@link DeveloperNameDto}.
     *
     * @return A list of {@link DeveloperNameDto} representing developers with the count of their active tasks.
     */
    @GetMapping("/active-task-count")
    public List<DeveloperNameDto> getAllDevelopersWithActiveTasksCount() {
        return developerService.getAllDevelopersWithActiveTasksCount();
    }

}
