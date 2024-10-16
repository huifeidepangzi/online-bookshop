package com.yi.online_bookshop.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yi.online_bookshop.controller.user.UserController;
import com.yi.online_bookshop.domain.entity.user.User;
import com.yi.online_bookshop.domain.entity.user.UserFactory;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void TestUserControllerE2EHappyPath() throws Exception {
        // Given
        LocalDateTime activeFrom = LocalDateTime.of(2024, 07, 01, 03, 00, 00);
        CreateUserDTO createUserDTO = new CreateUserDTO("Yi Shan", "yi.shan@test.com", 22, activeFrom);

        User mockUser = UserFactory.createUserFromUserDTO(createUserDTO);
        Mockito.when(userService.createUser(ArgumentMatchers.any(CreateUserDTO.class))).thenReturn(mockUser);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Data created successfully."));
    }

    @Test
    public void TestValidationErrorWithInvalidEmailAndName() throws Exception {
        // Given
        LocalDateTime activeFrom = LocalDateTime.of(2024, 07, 01, 03, 00, 00);
        CreateUserDTO createUserDTO = new CreateUserDTO("", "INVALID_EMAIL", 22, activeFrom);

        User mockUser = UserFactory.createUserFromUserDTO(createUserDTO);
        Mockito.when(userService.createUser(ArgumentMatchers.any(CreateUserDTO.class))).thenReturn(mockUser);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Validation failed."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subErrors[0].field").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subErrors[0].message").value("Email should be valid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subErrors[1].field").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subErrors[1].message").value("Name is required"));

    }
}
