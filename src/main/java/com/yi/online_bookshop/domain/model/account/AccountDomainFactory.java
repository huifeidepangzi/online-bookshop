package com.yi.online_bookshop.domain.model.account;

import com.yi.online_bookshop.domain.model.user.UserDomain;

public class AccountDomainFactory {

    public static AccountDomain createForUser(UserDomain user) {
        return new AccountDomain(user);
    }
}
