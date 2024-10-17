package com.yi.online_bookshop.domain.service.paymentProvider.implmentations;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.yi.online_bookshop.domain.service.paymentProvider.PaymentProviderInterface;

@Service
@Profile("prod")
public class ProdPaymentProvider implements PaymentProviderInterface {

    @Override
    public void sendToProvider() {
        System.out.println("Production payment sending complete.");
    }
}
