package com.taskmanager.dto;

public record UserResponseDto(
        Long id,
        String name,
        String email
) { }
