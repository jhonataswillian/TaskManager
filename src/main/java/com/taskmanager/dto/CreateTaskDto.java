package com.taskmanager.dto;

import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTaskDto(
        @NotBlank(message = "Title is required")
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,

        @Future(message = "Due date must be in the future")
        LocalDateTime dueDate
) {}