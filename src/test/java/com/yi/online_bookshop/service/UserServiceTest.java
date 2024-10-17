package com.yi.online_bookshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.yi.online_bookshop.domain.model.account.AccountDomain;
import com.yi.online_bookshop.domain.model.account.AccountDomainFactory;
import com.yi.online_bookshop.domain.model.account.AccountMapper;
import com.yi.online_bookshop.domain.model.user.UserDomain;
import com.yi.online_bookshop.domain.model.user.UserDomainFactory;
import com.yi.online_bookshop.domain.model.user.UserMapper;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.entity.AccountEntity;
import com.yi.online_bookshop.entity.UserEntity;

import com.yi.online_bookshop.repository.AccountRepository;
import com.yi.online_bookshop.repository.UserRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void TestUserCreationHappyPath() {
        // Given
        String name = "Yi Shan";
        String email = "yi.shan@test.com";
        Integer age = 22;
        LocalDateTime activeFrom = LocalDateTime.of(2024, 7, 1, 3, 0, 0);

        CreateUserDTO userDTO = new CreateUserDTO(name, email, age, activeFrom);
        UserDomain mockUser = UserDomainFactory.createUserFromUserDTO(userDTO);
        UserEntity mockUserEntity = UserMapper.DomainToEntity(mockUser);
        AccountDomain mockAccountDomain = AccountDomainFactory.createForUser(mockUser);
        AccountEntity mockAccountEntity = AccountMapper.DomainToEntity(mockAccountDomain);

        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(mockUserEntity);
        Mockito.when(accountRepository.save(any(AccountEntity.class))).thenReturn(mockAccountEntity);

        // When
        UserDomain userCreated = userService.createUser(userDTO);

        // Then
        assertNotNull(userCreated);
        assertEquals(name, userCreated.getName());
        assertEquals(email, userCreated.getEmail());
        assertEquals(age, userCreated.getAge());
        assertEquals(activeFrom, userCreated.getActiveFrom());
    }

    @Test
    public void TestRaiseDataIntegrityViolationError() {

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
