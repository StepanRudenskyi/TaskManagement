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

    // TODO: add documentation
    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public DeveloperDto createDeveloper(@RequestBody DeveloperDto developerDto) {
        return developerService.createDeveloper(developerDto);
    }

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
