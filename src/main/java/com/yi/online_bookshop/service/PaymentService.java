package com.yi.online_bookshop.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yi.online_bookshop.domain.model.payment.PaymentDomain;
import com.yi.online_bookshop.domain.model.payment.PaymentMapper;
import com.yi.online_bookshop.domain.service.paymentProvider.PaymentProviderInterface;
import com.yi.online_bookshop.dto.payment.CreatePaymentDTO;
import com.yi.online_bookshop.entity.AccountEntity;
import com.yi.online_bookshop.entity.PaymentEntity;
import com.yi.online_bookshop.entity.PaymentEntityFactory;
import com.yi.online_bookshop.repository.AccountRepository;
import com.yi.online_bookshop.repository.PaymentRepository;

@Service
public class PaymentService {

    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentProviderInterface paymentProvider;

    public PaymentService(AccountRepository accountRepository, PaymentRepository paymentRepository, PaymentProviderInterface paymentProvider) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.paymentProvider = paymentProvider;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentDomain createPaymentByAccountNumber(CreatePaymentDTO createPaymentDTO)
            throws EmptyResultDataAccessException {

        AccountEntity accountEntity = accountRepository.findByAccountNumber(createPaymentDTO.getAccountNumber());
        if (accountEntity == null) {
            throw new EmptyResultDataAccessException(1);
        }

        PaymentEntity paymentEntity = PaymentEntityFactory.createByAccountEntityAndDTO(accountEntity, createPaymentDTO);

        PaymentEntity savedPayment = this.paymentRepository.save(paymentEntity);
        PaymentDomain paymentDomain = PaymentMapper.EntityToDomain(savedPayment);

        // Call bank payment provider API
        paymentProvider.sendToProvider();

        return paymentDomain;
    }
}
