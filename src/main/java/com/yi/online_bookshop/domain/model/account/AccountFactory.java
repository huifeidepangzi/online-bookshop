package com.yi.online_bookshop.domain.model.account;

import com.yi.online_bookshop.domain.model.user.User;

public class AccountFactory {

    public static Account createForUser(User user) {
        return new Account(user);
    }
}
