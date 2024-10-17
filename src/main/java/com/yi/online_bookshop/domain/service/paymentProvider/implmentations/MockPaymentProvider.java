package com.yi.online_bookshop.domain.service.paymentProvider.implmentations;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.yi.online_bookshop.domain.service.paymentProvider.PaymentProviderInterface;

@Service
@Profile("mock")
public class MockPaymentProvider implements PaymentProviderInterface {

    @Override
    public void sendToProvider() {
        System.out.println("Mocked payment sending complete.");
    }
}
