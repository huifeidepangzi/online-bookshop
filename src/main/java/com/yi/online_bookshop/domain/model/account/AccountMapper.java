package com.yi.online_bookshop.domain.model.account;

import com.yi.online_bookshop.domain.model.user.UserDomain;
import com.yi.online_bookshop.domain.model.user.UserMapper;
import com.yi.online_bookshop.entity.AccountEntity;
import com.yi.online_bookshop.entity.UserEntity;

public class AccountMapper {

    public final static AccountEntity DomainToEntity(AccountDomain accountDomain) {
        AccountEntity accountEntity = new AccountEntity();
        UserDomain userDomain = accountDomain.getUser();
        UserEntity userEntity = UserMapper.DomainToEntity(userDomain);
        accountEntity.setUser(userEntity);
        accountEntity.setAccountNumber(accountDomain.getAccountNumber());
        return accountEntity;
    }
}
