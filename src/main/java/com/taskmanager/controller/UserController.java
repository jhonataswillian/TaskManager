package com.taskmanager.controller;

import com.taskmanager.dto.CreateUserDto;
import com.taskmanager.dto.UserResponseDto;
import com.taskmanager.entity.User;
import com.taskmanager.entity.UserRole;
import com.taskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid CreateUserDto dto) {

        User newUser = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .role(UserRole.USER)
                .build();

        User savedUser = userService.save(newUser);

        UserResponseDto responde = new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responde);
    }
}
