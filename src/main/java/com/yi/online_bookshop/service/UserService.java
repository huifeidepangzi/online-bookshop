package com.yi.online_bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yi.online_bookshop.domain.entity.account.Account;
import com.yi.online_bookshop.domain.entity.account.AccountFactory;
import com.yi.online_bookshop.domain.entity.user.User;
import com.yi.online_bookshop.domain.entity.user.UserFactory;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.repository.AccountRepository;
import com.yi.online_bookshop.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public User createUser(CreateUserDTO createUserDTO) throws DataIntegrityViolationException {
        User newUser = UserFactory.createUserFromUserDTO(createUserDTO);
        User savedUser = userRepository.save(newUser);

        Account account = AccountFactory.createForUser(savedUser);
        accountRepository.save(account);

        return savedUser;
    }
}
