package org.example.taskmanagement.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskmanagement.dto.DeveloperDto;
import org.example.taskmanagement.dto.DeveloperNameDto;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.mapper.DeveloperMapper;
import org.example.taskmanagement.mapper.TaskMapper;
import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.projection.DeveloperNameProjection;
import org.example.taskmanagement.repository.DeveloperRepository;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {
    private final DeveloperMapper developerMapper;
    private final DeveloperRepository developerRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Autowired
    public DeveloperService(DeveloperMapper developerMapper, DeveloperRepository developerRepository,
                            TaskRepository taskRepository, TaskMapper taskMapper) {
        this.developerMapper = developerMapper;
        this.developerRepository = developerRepository;
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public DeveloperDto createDeveloper(DeveloperDto developerDto) {
        developerDto.setId(null);
        Developer entity = developerMapper.toEntity(developerDto);
        return developerMapper.toDto(developerRepository.save(entity));
    }

    public DeveloperDto getDeveloperById(Long developerId) {

        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            return developerMapper.toDto(optionalDeveloper.get());
        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }

    public void updateDeveloper(Long developerId, DeveloperDto updatedDeveloperDto) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            Developer existingDev = optionalDeveloper.get();

            existingDev.setName(updatedDeveloperDto.getName());
            existingDev.setEmail(updatedDeveloperDto.getEmail());

            developerRepository.save(existingDev);
        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }

    public void deleteDeveloper(Long developerId) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            developerRepository.deleteById(developerId);
        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }

    // possibility to create Developer with developer's task via one REST call
    public void associateTasksWithDeveloper(Long developerId, List<TaskDto> taskDtos) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();

            List<Task> tasks = taskDtos.stream()
                    .map(taskMapper::toEntity)
                    .toList();

            developer.setTasks(tasks);
            developerRepository.save(developer);

        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }

    public List<TaskDto> getTaskByDeveloperId(Long developerId) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            List<Task> tasks = developer.getTasks();

            List<TaskDto> taskDtos = tasks.stream()
                    .map(taskMapper::toDto)
                    .toList();
            return taskDtos;
        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }


    public List<DeveloperNameDto> getAllDevelopersWithActiveTasks() {
        List<DeveloperNameProjection> projections = developerRepository.findAllActiveTasks();
        return projections.stream()
                .map(projection -> {
                    Developer developer = developerRepository.findById(projection.getId())
                            .orElseThrow();
                    return new DeveloperNameDto(developer.getId(), developer.getName(), projection.getActiveTasks());
                })
                .toList();
    }

    public List<DeveloperNameDto> getAllDevelopersWithActiveTasksCount() {
        List<DeveloperNameProjection> tasksCount = developerRepository.findAllDevelopersWithActiveTasksCount();

        return tasksCount.stream()
                .map(projection -> {
                    Developer developer = developerRepository.findById(projection.getId()).orElseThrow();
                    return new DeveloperNameDto(developer.getId(), developer.getName(), projection.getActiveTasks());
                })
                .toList();
    }

}
