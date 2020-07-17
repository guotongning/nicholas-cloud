package com.ning.springcloud.cloud.service;


import com.ning.springcloud.api.dto.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

    public Payment getPaymentByOrderNo(String orderNo);
}
