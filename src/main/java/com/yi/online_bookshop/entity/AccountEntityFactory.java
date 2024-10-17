package com.yi.online_bookshop.entity;

public class AccountEntityFactory {

    public final static AccountEntity createFromUserEntity(UserEntity userEntity) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUser(userEntity);
        return accountEntity;
    }
}
