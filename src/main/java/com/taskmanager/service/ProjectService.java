package com.taskmanager.service;

import com.taskmanager.dto.CreateProjectDto;
import com.taskmanager.dto.UpdateProjectDto;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.User;
import com.taskmanager.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public Project save(CreateProjectDto data, User user) {
        Project project = Project.builder()
                .title(data.title())
                .description(data.description())
                .user(user)
                .build();

        return projectRepository.save(project);
    }

    @Transactional
    public Project update(Long id, UpdateProjectDto data, User user) {
        Project project = this.findByIdAndUser(id, user);

        project.update(data.title(), data.description());

        return projectRepository.save(project);
    }

    public Page<Project> findAllByUser(User user, Pageable pageable) {
        return projectRepository.findByUserId(user.getId(), pageable);
    }

    public Project findByIdAndUser(Long id, User user) {
        return projectRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    @Transactional
    public void delete(Long id, User user) {
        Project project = this.findByIdAndUser(id, user);
        projectRepository.delete(project);
    }
}