package com.yi.online_bookshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.yi.online_bookshop.domain.entity.user.User;
import com.yi.online_bookshop.domain.entity.user.UserFactory;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.repository.UserRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void TestUserCreationHappyPath() {
        // Given
        String name = "Yi Shan";
        String email = "yi.shan@test.com";
        Integer age = 22;
        LocalDateTime activeFrom = LocalDateTime.of(2024, 7, 1, 3, 0, 0);

        CreateUserDTO userDTO = new CreateUserDTO(name, email, age, activeFrom);
        User mockUser = UserFactory.createUserFromUserDTO(userDTO);

        Mockito.when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // When
        User userCreated = userService.createUser(userDTO);

        // Then
        assertNotNull(userCreated);
        assertEquals(name, userCreated.getName());
        assertEquals(email, userCreated.getEmail());
        assertEquals(age, userCreated.getAge());
        assertEquals(activeFrom, userCreated.getActiveFrom());
    }

    @Test
    public void TestRaiseDataIntegrityViolationError() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Given
        String name = "Yi Shan";
        String email = "yi.shan@test.com";
        Integer age = 22;
        LocalDateTime activeFrom = LocalDateTime.of(2024, 7, 1, 3, 0, 0);

        CreateUserDTO userDTO = new CreateUserDTO(name, email, age, activeFrom);

        // And
        when(userRepository.save(any()))
                .thenThrow(new DataIntegrityViolationException("Email is existing."));

        // When & Then
        DataIntegrityViolationException thrown = assertThrows(DataIntegrityViolationException.class, () -> {
            userService.createUser(userDTO);
        });

        assertEquals(thrown.getMessage(), "Email is existing.");
    }
}
