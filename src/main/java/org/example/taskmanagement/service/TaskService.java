package org.example.taskmanagement.service;



import jakarta.persistence.EntityNotFoundException;
//import javax.persistence.EntityNotFoundException;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.mapper.TaskMapper;
import org.example.taskmanagement.model.Task;
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

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
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
        List<TaskDto> taskDtos = tasks.stream()
                .map(task -> taskMapper.toDto(task))
                .toList();

        return taskDtos;
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
}
