package com.yi.online_bookshop.domain.model.user;

import java.time.LocalDateTime;
import java.util.List;

import com.yi.online_bookshop.domain.model.account.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    private User() {
    }

    User(String name, String email, Integer age, LocalDateTime activeFrom) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.activeFrom = activeFrom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = true)
    private Integer age;

    @Column(name = "active_from", nullable = false)
    private LocalDateTime activeFrom;

    @Column(name = "active_to") // Allows null value by default
    private LocalDateTime activeTo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;
}
