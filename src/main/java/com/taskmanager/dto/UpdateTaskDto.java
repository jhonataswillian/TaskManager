package com.taskmanager.dto;

import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record UpdateTaskDto(
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,

        @Future(message = "Due data must be in the future")
        LocalDateTime dueDate
) {
}