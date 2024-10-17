package com.yi.online_bookshop.domain.model.user;

import com.yi.online_bookshop.entity.UserEntity;

public class UserMapper {

    public final static UserEntity DomainToEntity(UserDomain userDomain) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDomain.getName());
        userEntity.setEmail(userDomain.getEmail());
        userEntity.setAge(userDomain.getAge());
        userEntity.setActiveFrom(userDomain.getActiveFrom());
        return userEntity;
    }

}
