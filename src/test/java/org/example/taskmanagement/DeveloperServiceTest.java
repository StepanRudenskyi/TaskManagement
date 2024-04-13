package org.example.taskmanagement;

import org.example.taskmanagement.dto.DeveloperDto;
import org.example.taskmanagement.dto.TaskDto;
import org.example.taskmanagement.mapper.DeveloperMapper;
import org.example.taskmanagement.mapper.TaskMapper;
import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.repository.DeveloperRepository;
import org.example.taskmanagement.service.DeveloperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {
    @Mock
    private DeveloperRepository developerRepository;
    @Mock
    private DeveloperMapper developerMapper;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private DeveloperService developerService;

    @Test
    void testGetDeveloperById() {
        Long developerId = 1L;
        Developer developer = new Developer();
        developer.setId(developerId);
        DeveloperDto expectedDto = new DeveloperDto();
        expectedDto.setId(developerId);

        when(developerRepository.findById(developerId)).thenReturn(Optional.of(developer));
        when(developerMapper.toDto(developer)).thenReturn(expectedDto);

        DeveloperDto result = developerService.getDeveloperById(developerId);

        assertEquals(expectedDto, result);
    }

    @Test
    void testCreateDeveloper() {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setName("Stepan");
        developerDto.setName("stepan@example.com");

        Developer developer = new Developer();
        developer.setId(1L);
        developer.setName(developerDto.getName());
        developer.setEmail(developerDto.getEmail());

        when(developerMapper.toEntity(developerDto)).thenReturn(developer);
        when(developerRepository.save(developer)).thenReturn(developer);
        when(developerMapper.toDto(developer)).thenReturn(developerDto);

        DeveloperDto createdDeveloper = developerService.createDeveloper(developerDto);

        assertEquals(developerDto.getName(), createdDeveloper.getName());
        assertEquals(developerDto.getEmail(), createdDeveloper.getEmail());
        //   assertNotNull(createdDeveloper.getId());
    }

    @Test
    void testUpdateDeveloper() {
        Long developerId = 1L;
        DeveloperDto updatedDto = new DeveloperDto();
        updatedDto.setName("Updated");
        updatedDto.setEmail("upd@example.com");

        Developer existingDeveloper = new Developer();
        existingDeveloper.setId(developerId);
        existingDeveloper.setName("Original");
        existingDeveloper.setEmail("original@example.com");

        when(developerRepository.findById(developerId)).thenReturn(Optional.of(existingDeveloper));
        when(developerRepository.save(existingDeveloper)).thenReturn(existingDeveloper);

        developerService.updateDeveloper(developerId, updatedDto);

        assertEquals(updatedDto.getName(), existingDeveloper.getName());
        assertEquals(updatedDto.getEmail(), existingDeveloper.getEmail());
    }

    @Test
    void testDeleteDeveloper() {
        Long developerId = 1L;
        Developer existingDeveloper = new Developer();
        existingDeveloper.setId(developerId);

        when(developerRepository.findById(developerId)).thenReturn(Optional.of(existingDeveloper));

        developerService.deleteDeveloper(developerId);

        Mockito.verify(developerRepository).deleteById(developerId);
    }

    @Test
    void testGetTaskByDeveloperId() {
        Long developerId = 1L;
        Developer developer = new Developer();
        developer.setId(developerId);

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        developer.setTasks(Arrays.asList(task1, task2));

        when(developerRepository.findById(developerId)).thenReturn(Optional.of(developer));
        when(taskMapper.toDto(Mockito.any(Task.class))).thenAnswer(invocation -> {
            Task task = invocation.getArgument(0);
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTitle(task.getTitle());
            return taskDto;
        });

        List<TaskDto> taskDtos = developerService.getTaskByDeveloperId(developerId);

        assertEquals(2, taskDtos.size());
        assertEquals("Task 1", taskDtos.get(0).getTitle());
        assertEquals("Task 2", taskDtos.get(1).getTitle());
    }

}
