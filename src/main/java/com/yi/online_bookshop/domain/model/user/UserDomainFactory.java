package com.yi.online_bookshop.domain.model.user;

import com.yi.online_bookshop.dto.user.CreateUserDTO;

public class UserDomainFactory {

    public static UserDomain createUserFromUserDTO(CreateUserDTO createUserDTO) {
        return new UserDomain(createUserDTO.getName(), createUserDTO.getEmail(), createUserDTO.getAge(), createUserDTO.getActiveFrom());
    }
}
