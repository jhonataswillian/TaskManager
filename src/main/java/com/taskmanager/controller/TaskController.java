package com.taskmanager.controller;

import com.taskmanager.dto.CreateTaskDto;
import com.taskmanager.dto.TaskResponseDto;
import com.taskmanager.dto.UpdateTaskDto;
import com.taskmanager.entity.User;
import com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(
            @PathVariable Long projectId,
            @RequestBody @Valid CreateTaskDto data,
            @AuthenticationPrincipal User user,
            UriComponentsBuilder uriBuilder
            ) {
        var task = taskService.save(projectId, data, user);

        var uri = uriBuilder.path("/projects/{projectId}/tasks/{taskId}")
                .buildAndExpand(projectId, task.getId()).toUri();

        return ResponseEntity.created(uri).body(TaskResponseDto.fromEntity(task));
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> list(
            @PathVariable Long projectId,
            @AuthenticationPrincipal User user,
            Pageable pageable
    ) {
        var page = taskService.findAllByProject(projectId, user, pageable)
                .map(TaskResponseDto::fromEntity);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> findById(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @AuthenticationPrincipal User user
    ) {
        var task = taskService.findByIdAndProject(projectId, taskId, user);
        return ResponseEntity.ok(TaskResponseDto.fromEntity(task));
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> update(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody @Valid UpdateTaskDto data,
            @AuthenticationPrincipal User user
            ) {
        var task = taskService.update(projectId, taskId, data, user);
        return ResponseEntity.ok(TaskResponseDto.fromEntity(task));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @AuthenticationPrincipal User user
    ) {
        taskService.delete(projectId, taskId, user);
        return ResponseEntity.noContent().build();
    }
}