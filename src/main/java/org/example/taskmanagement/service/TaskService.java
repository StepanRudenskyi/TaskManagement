package org.example.taskmanagement.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new EntityNotFoundException("Task with ID: " + taskId + " not found");
        }
    }

    public void updateTask(Long taskId, Task updatedTask) {
        Optional<Task> existingOptionalTask = taskRepository.findById(taskId);

        if (existingOptionalTask.isPresent()) {
            Task existingTask = existingOptionalTask.get();

            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());

            taskRepository.save(existingTask);
        } else {
            throw new EntityNotFoundException("Task with ID: " + taskId + " not found");
        }
    }

    public void deleteTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            taskRepository.deleteById(taskId);
        } else {
            throw new EntityNotFoundException("Task with ID: " + taskId + " not found");
        }
    }
}
