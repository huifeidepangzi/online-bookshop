package com.yi.online_bookshop.domain.model.payment;

import com.yi.online_bookshop.entity.PaymentEntity;

public class PaymentMapper {

    public final static PaymentDomain EntityToDomain(PaymentEntity paymentEntity) {
        PaymentDomain paymentDomain = new PaymentDomain(
                paymentEntity.getId(),
                paymentEntity.getAccount(),
                paymentEntity.getPaymentType(),
                paymentEntity.getAmount(),
                paymentEntity.getPaymentDate()
        );
        return paymentDomain;
    }
}
