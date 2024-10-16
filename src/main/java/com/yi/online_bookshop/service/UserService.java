package com.yi.online_bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yi.online_bookshop.domain.entity.UserFactory;
import com.yi.online_bookshop.domain.entity.user.User;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(CreateUserDTO createUserDTO) throws DataIntegrityViolationException {
        User newUser = UserFactory.createUserFromUserDTO(createUserDTO);
        userRepository.save(newUser);
        return newUser;
    }
}
