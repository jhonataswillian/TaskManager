package com.taskmanager.service;

import com.taskmanager.dto.CreateTaskDto;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.User;
import com.taskmanager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Should create task successfully when project belongs to user ")
    void save_Success() {
        User user = User.builder().id(1L).build();
        Project project = Project.builder().id(10L).user(user).build();
        CreateTaskDto dto = new CreateTaskDto("New Task",
                "Description",
                null,
                TaskPriority.HIGH,
                null);
        Task task = Task.builder().title("New Task"). project(project).build();

        when(projectService.findByIdAndUser(10L, user)).thenReturn(project);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task savedTask = taskService.save(10L, dto, user);

        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Should throw exception when project does not belong to user")
    void save_Failure_ProjectNotOwned() {
        User user = User.builder().id(1L).build();
        CreateTaskDto dto = new CreateTaskDto("New Task", "Desc",
                null, null, null);

        when(projectService.findByIdAndUser(10L, user)).thenThrow(new EntityNotFoundException("Project not found"));

        assertThrows(EntityNotFoundException.class, () -> taskService.save(10L, dto, user));
        verify(taskRepository, never()).save(any(Task.class));
    }
}