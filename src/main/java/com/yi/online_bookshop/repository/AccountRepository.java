package com.yi.online_bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.yi.online_bookshop.entity.AccountEntity;

import jakarta.persistence.LockModeType;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    AccountEntity findByAccountNumber(String accountNumber);
}
