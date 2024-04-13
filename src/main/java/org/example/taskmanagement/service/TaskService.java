package org.example.taskmanagement.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.mapper.TaskMapper;
import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.repository.DeveloperRepository;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final DeveloperRepository developerRepository;


    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, DeveloperRepository developerRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.developerRepository = developerRepository;
    }

    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        taskDto.setId(null);
        Task entity = taskMapper.toEntity(taskDto);
        return taskMapper.toDto(taskRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getAllTask() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskDto getTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            return taskMapper.toDto(optionalTask.get());
        } else {
            throw new EntityNotFoundException("Task with ID: " + taskId + " not found");
        }
    }

    @Transactional
    public void updateTask(Long taskId, TaskDto updatedTaskDto) {
        Optional<Task> existingOptionalTask = taskRepository.findById(taskId);

        if (existingOptionalTask.isPresent()) {
            Task existingTask = existingOptionalTask.get();

            existingTask.setTitle(updatedTaskDto.getTitle());
            existingTask.setDescription(updatedTaskDto.getDescription());
            existingTask.setStatus(updatedTaskDto.getStatus());

            taskRepository.save(existingTask);
        } else {
            throw new EntityNotFoundException("Task with ID: " + taskId + " not found");
        }
    }

    @Transactional
    public void deleteTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            taskRepository.deleteById(taskId);
        } else {
            throw new EntityNotFoundException("Task with ID: " + taskId + " not found");
        }
    }

    @Transactional
    public void associateTaskWithDeveloper(Long taskId, Long developerId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found"));
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new EntityNotFoundException("Developer with ID " + developerId + " not found"));

        task.setDeveloper(developer);
        taskRepository.save(task);
    }
}
