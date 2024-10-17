package com.yi.online_bookshop.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yi.online_bookshop.domain.model.user.UserDomain;
import com.yi.online_bookshop.domain.model.user.UserDomainFactory;
import com.yi.online_bookshop.domain.model.user.UserMapper;
import com.yi.online_bookshop.dto.user.CreateUserDTO;
import com.yi.online_bookshop.entity.AccountEntity;
import com.yi.online_bookshop.entity.AccountEntityFactory;
import com.yi.online_bookshop.entity.UserEntity;
import com.yi.online_bookshop.repository.AccountRepository;
import com.yi.online_bookshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

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
