package com.taskmanager.dto;

import com.taskmanager.entity.Project;

import java.time.LocalDateTime;

public record ProjectResponseDto(
        Long id,
        String title,
        String description,
        LocalDateTime createdAt
) {
    public static ProjectResponseDto fromEntity(Project project) {
        return new ProjectResponseDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getCreatedAt()
        );
    }
}