package com.yi.online_bookshop.domain.model.account;

import com.yi.online_bookshop.domain.model.user.User;

public class AccountDomainFactory {

    public static AccountDomain createForUser(User user) {
        return new AccountDomain(user);
    }
}
