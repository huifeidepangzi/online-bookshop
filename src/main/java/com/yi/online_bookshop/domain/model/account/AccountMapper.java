package com.yi.online_bookshop.domain.model.account;

import com.yi.online_bookshop.entity.AccountEntity;

public class AccountMapper {

    public final static AccountEntity DomainToEntity(AccountDomain accountDomain) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUser(accountDomain.getUser());
        accountEntity.setAccountNumber(accountDomain.getAccountNumber());
        return accountEntity;
    }

    public final static AccountDomain EntityToDomain(AccountEntity accountEntity) {
        AccountDomain accountDomain = new AccountDomain(accountEntity.getUser(), accountEntity.getAccountNumber());
        return accountDomain;
    }

}
