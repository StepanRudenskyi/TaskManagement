package org.example.taskmanagement.controller;

import org.example.taskmanagement.model.Task;
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

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        taskService.updateTask(taskId, updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
}
