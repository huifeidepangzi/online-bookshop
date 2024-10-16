package com.yi.online_bookshop.domain.entity.account;

import com.yi.online_bookshop.domain.entity.user.User;

public class AccountFactory {

    public static Account createForUser(User user) {
        return new Account(user);
    }
}
