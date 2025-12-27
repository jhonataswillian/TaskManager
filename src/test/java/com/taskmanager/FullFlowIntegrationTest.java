package com.taskmanager;

import com.taskmanager.dto.CreateProjectDto;
import com.taskmanager.dto.CreateUserDto;
import com.taskmanager.dto.LoginResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FullFlowIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Should execute full flow: SignUp -> Login -> Create Project")
    void fullFlow() {

        // 1. Sign up
        CreateUserDto signUpDto = new CreateUserDto("Integration Hero", "hero@test.com", "123456");
        ResponseEntity<Void> signUpResponse = restTemplate.postForEntity("/users", signUpDto, Void.class);
                        
        assertEquals(HttpStatus.CREATED, signUpResponse.getStatusCode());

        // 2. Login
        Map<String, String> loginRequest = Map.of("email", "hero@test.com", "password", "123456");
        ResponseEntity<LoginResponseDto> loginResponse = restTemplate.postForEntity("/auth/login", loginRequest, LoginResponseDto.class);

        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        assertNotNull(loginResponse.getBody());
        String token = loginResponse.getBody().token();
        assertNotNull(token);

        // 3. Create Project (Using Token)
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        CreateProjectDto projectDto = new CreateProjectDto("Integration Project", "Testing with Docker");
        HttpEntity<CreateProjectDto> entity = new HttpEntity<>(projectDto, headers);

        ResponseEntity<String> projectResponse = restTemplate.exchange("/projects",
                HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.CREATED, projectResponse.getStatusCode());
    }
}