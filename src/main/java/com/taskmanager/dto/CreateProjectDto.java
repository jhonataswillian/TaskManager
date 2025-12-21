package com.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProjectDto(
        @NotBlank(message = "Title is required")
        @Size(min = 3, message = "Title must be at least 3 characters long")
        String title,
        String description
) {}