package org.example.taskmanagement.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * RESTful endpoint to create a new task.
     * <p>
     * This endpoint allows clients to create a new task by providing the necessary information
     * in the request body. The information should be in the form of a JSON object. The created task
     * is returned as a response in the form of a {@link TaskDto}.
     *
     * @param taskDto The task details provided in the request body.
     * @return A {@link TaskDto} representing the newly created task.
     */
    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    /**
     * RESTful endpoint to retrieve all tasks.
     * <p>
     * This endpoint allows clients to retrieve all tasks. If tasks exist, their details
     * are returned as a response in the form of a list of {@link TaskDto}.
     *
     * @return A list of {@link TaskDto} representing all tasks.
     */
    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTask();
    }

    /**
     * RESTful endpoint to retrieve a task by its unique identifier.
     * <p>
     * This endpoint allows clients to retrieve details of a specific task by providing
     * the task's ID as a path variable. If the task with the specified ID exists,
     * its details are returned as a response in the form of a {@link TaskDto}.
     *
     * @param taskId The unique identifier of the task to be retrieved.
     * @return A {@link TaskDto} representing the details of the requested task.
     * @throws EntityNotFoundException if the task with the provided taskId was not found.
     */
    @GetMapping("/{taskId}")
    public TaskDto getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    /**
     * RESTful endpoint to update an existing task.
     * <p>
     * This endpoint allows clients to update an existing task by providing the updated
     * details in the request body. The taskId in the path variable specifies the task
     * to be updated.
     *
     * @param taskId The unique identifier of the task to be updated.
     * @param updatedTaskDto The updated details of the task provided in the request body.
     */
    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody TaskDto updatedTaskDto) {
        taskService.updateTask(taskId, updatedTaskDto);
    }

    /**
     * RESTful endpoint to delete a task by its unique identifier.
     * <p>
     * This endpoint allows clients to delete a specific task by providing the task's ID
     * as a path variable. If the task with the specified ID exists, it will be deleted.
     *
     * @param taskId The unique identifier of the task to be deleted.
     * @throws EntityNotFoundException if the task with the provided taskId was not found.
     */
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }

    /**
     * RESTful endpoint to associate a task with a developer.
     * <p>
     * This endpoint allows clients to associate a specific task with a developer by providing
     * the task's ID and the developer's ID as path variables.
     *
     * @param taskId The unique identifier of the task to be associated with the developer.
     * @param developerId The unique identifier of the developer with whom the task will be associated.
     */
    @PostMapping("/{taskId}/associate-developer/{developerId}")
    public void associateTaskWithDeveloper(@PathVariable Long taskId, @PathVariable Long developerId) {
        taskService.associateTaskWithDeveloper(taskId, developerId);
    }
}
