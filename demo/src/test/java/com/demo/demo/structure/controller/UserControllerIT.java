package com.demo.demo.structure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.demo.structure.model.dto.CreateUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_shouldReturn200AndUserResource() throws Exception {
        CreateUserDto dto = new CreateUserDto();
        dto.setName("Mario");
        dto.setLastname("Rossi");
        dto.setEmail("mario.rossi@example.com");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void createUser_shouldReturn400WhenNameIsNull() throws Exception {
        CreateUserDto dto = new CreateUserDto();
        dto.setName(null);
        dto.setLastname("Rossi");
        dto.setEmail("mario.rossi@example.com");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}