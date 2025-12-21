package com.taskmanager.controller;

import com.taskmanager.dto.CreateProjectDto;
import com.taskmanager.dto.ProjectResponseDto;
import com.taskmanager.dto.UpdateProjectDto;
import com.taskmanager.entity.User;
import com.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDto> create(
            @RequestBody @Valid CreateProjectDto data,
            @AuthenticationPrincipal User user,
            UriComponentsBuilder uriBuilder
            ) {
        var project = projectService.save(data, user);
        var uri = uriBuilder.path("/projects/{id}").buildAndExpand(project.getId()).toUri();

        return ResponseEntity.created(uri).body(ProjectResponseDto.fromEntity(project));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDto>> list(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable
            ) {
        var page = projectService.findAllByUser(user, pageable)
                .map(ProjectResponseDto::fromEntity);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> findById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        var project = projectService.findByIdAndUser(id, user);
        return ResponseEntity.ok(ProjectResponseDto.fromEntity(project));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProjectDto data,
            @AuthenticationPrincipal User user
            ) {
        var project = projectService.update(id, data, user);
        return ResponseEntity.ok(ProjectResponseDto.fromEntity(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        projectService.delete(id, user);
        return ResponseEntity.noContent().build();
    }
}