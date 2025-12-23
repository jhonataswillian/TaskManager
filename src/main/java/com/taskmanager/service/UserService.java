package com.taskmanager.service;

import com.taskmanager.entity.User;
import com.taskmanager.exception.UserAlreadyExistsException;
import com.taskmanager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        User userToSave = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        return userRepository.save(userToSave);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
