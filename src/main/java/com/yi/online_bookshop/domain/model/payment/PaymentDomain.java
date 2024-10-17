package com.yi.online_bookshop.domain.model.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.yi.online_bookshop.entity.AccountEntity;
import com.yi.online_bookshop.enums.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDomain {

    private Long id;

    private AccountEntity account;

    private PaymentType paymentType;

    private BigDecimal amount;

    private LocalDate paymentDate;

}
