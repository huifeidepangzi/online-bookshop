package com.yi.online_bookshop.domain.model.user;

import com.yi.online_bookshop.dto.user.CreateUserDTO;

public class UserFactory {

    public static User createUserFromUserDTO(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.getName(), createUserDTO.getEmail(), createUserDTO.getAge(), createUserDTO.getActiveFrom());
    }
}
