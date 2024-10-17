package com.yi.online_bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yi.online_bookshop.domain.model.user.UserDomain;
import com.yi.online_bookshop.domain.model.user.UserDomainFactory;
import com.yi.online_bookshop.domain.model.user.UserMapper;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.entity.AccountEntity;
import com.yi.online_bookshop.entity.AccountEntityFactory;
import com.yi.online_bookshop.entity.UserEntity;
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
    public UserDomain createUser(CreateUserDTO createUserDTO) throws DataIntegrityViolationException {
        UserDomain userDomain = UserDomainFactory.createUserFromUserDTO(createUserDTO);
        UserEntity userEntity = UserMapper.DomainToEntity(userDomain);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        AccountEntity accountEntity = AccountEntityFactory.createFromUserEntity(savedUserEntity);
        accountRepository.save(accountEntity);

        return userDomain;
    }
}
