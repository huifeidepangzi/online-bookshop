package com.yi.online_bookshop.domain.model.user;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDomain {

    UserDomain(String name, String email, Integer age, LocalDateTime activeFrom) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.activeFrom = activeFrom;
    }

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private LocalDateTime activeFrom;

    private LocalDateTime activeTo;
}
