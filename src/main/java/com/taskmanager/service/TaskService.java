package com.taskmanager.service;

import com.taskmanager.dto.CreateTaskDto;
import com.taskmanager.dto.UpdateTaskDto;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    @Transactional
    public Task save(Long projectId, CreateTaskDto data, User user) {

        Project project = projectService.findByIdAndUser(projectId, user);

        Task task = Task.builder()
                .title(data.title())
                .description(data.description())
                .status(data.status())
                .priority(data.priority())
                .dueDate(data.dueDate())
                .project(project)
                .build();

        return taskRepository.save(task);
    }

    public Page<Task> findAllByProject(Long projectId, User user, Pageable pageable) {
        projectService.findByIdAndUser(projectId, user);
        return taskRepository.findByProjectId(projectId, pageable);
    }

    public Task findByIdAndProject(Long projectId, Long taskId, User user) {
        projectService.findByIdAndUser(projectId, user);

        return taskRepository.findByIdAndProjectId(taskId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found in this project"));
    }

    @Transactional
    public Task update(Long projectId, Long taskId, UpdateTaskDto data, User user) {
        Task task = this.findByIdAndProject(projectId, taskId, user);

        task.update(
                data.title(),
                data.description(),
                data.status(),
                data.priority(),
                data.dueDate()
        );
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(Long projectId, Long taskId, User user) {
        Task task = this.findByIdAndProject(projectId, taskId, user);
        taskRepository.delete(task);
    }
}