package org.example.taskmanagement.service;

import jakarta.persistence.EntityNotFoundException;
//import javax.persistence.EntityNotFoundException;
import org.example.taskmanagement.dto.DeveloperDto;
import org.example.taskmanagement.dto.DeveloperNameDto;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.enums.TaskStatus;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeveloperService {
    private final DeveloperMapper developerMapper;
    private final DeveloperRepository developerRepository;
    private final TaskMapper taskMapper;


    @Autowired
    public DeveloperService(DeveloperMapper developerMapper, DeveloperRepository developerRepository,
                            TaskMapper taskMapper) {
        this.developerMapper = developerMapper;
        this.developerRepository = developerRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    public DeveloperDto createDeveloper(DeveloperDto developerDto) {
        developerDto.setId(null);
        Developer entity = developerMapper.toEntity(developerDto);
        return developerMapper.toDto(developerRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public DeveloperDto getDeveloperById(Long developerId) {

        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            return developerMapper.toDto(optionalDeveloper.get());
        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }

    @Transactional
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

    @Transactional
    public void deleteDeveloper(Long developerId) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            List<Task> tasks = developer.getTasks();

            tasks.forEach(task -> task.setDeveloper(null));

            developerRepository.deleteById(developerId);
        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }
    // TODO: possibility to create Developer with developer's task via one REST call

    @Transactional(readOnly = true)
    public List<TaskDto> getTaskByDeveloperId(Long developerId) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(developerId);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            List<Task> tasks = developer.getTasks();

            return tasks.stream()
                    .map(taskMapper::toDto)
                    .toList();
        } else {
            throw new EntityNotFoundException("Developer with ID: " + developerId + " not found");
        }
    }

    // TODO: fix
    @Transactional(readOnly = true)
    public List<DeveloperNameDto> getAllDevelopersWithActiveTasks() {
        List<DeveloperNameProjection> projections = developerRepository.findAllActiveTasks();

        return projections.stream()
                .map(projection -> new DeveloperNameDto(projection.getId(), projection.getName(), projection.getActiveTasks()))
                .collect(Collectors.toList());
    }

    // TODO: fix
    @Transactional(readOnly = true)
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
