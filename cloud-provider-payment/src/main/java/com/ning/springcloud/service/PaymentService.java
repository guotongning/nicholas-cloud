package com.ning.springcloud.service;

import com.ning.springcloud.entities.dao.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
