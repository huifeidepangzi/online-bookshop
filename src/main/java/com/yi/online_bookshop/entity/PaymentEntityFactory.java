package com.yi.online_bookshop.entity;

import com.yi.online_bookshop.dto.payment.CreatePaymentDTO;

public class PaymentEntityFactory {

    public final static PaymentEntity createByAccountEntityAndDTO(AccountEntity accountEntity, CreatePaymentDTO createPaymentDTO) {
        return new PaymentEntity(
                accountEntity,
                createPaymentDTO.getPaymentType(),
                createPaymentDTO.getAmount(),
                createPaymentDTO.getPaymentDate()
        );
    }
}
