package com.yi.online_bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yi.online_bookshop.domain.entity.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
