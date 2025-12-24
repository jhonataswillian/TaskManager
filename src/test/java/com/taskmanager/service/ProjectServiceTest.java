package com.taskmanager.service;

import com.taskmanager.dto.CreateProjectDto;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.User;
import com.taskmanager.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    @DisplayName("Should create project successfully")
    void save_Success() {
        User user = User.builder().id(1L).email("user@test.com").build();
        CreateProjectDto dto = new CreateProjectDto("My Project", "Description");
        Project project = Project.builder().title("My Project").user(user).build();

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project savedProject = projectService.save(dto, user);

        assertNotNull(savedProject);
        assertEquals("My Project", savedProject.getTitle());
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    @DisplayName("Should return project when user is owner")
    void findByIdAndUser_Success() {
        User user = User.builder().id(1L).build();
        Project project = Project.builder().id(10L).user(user).build();

        when(projectRepository.findByIdAndUserId(10L, 1L)).thenReturn(Optional.of(project));

        Project result = projectService.findByIdAndUser(10L, user);

        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    @DisplayName("Should throw exception when project not found or user not owner")
    void findByIdAndUser_Failure() {
        User user = User.builder().id(1L).build();
        
        when(projectRepository.findByIdAndUserId(99L, 1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> projectService.findByIdAndUser(99L, user));
    }
}
