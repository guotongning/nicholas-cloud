package com.ning.springcloud.api;

import com.ning.springcloud.entities.dao.Payment;
import com.ning.springcloud.response.CommonResult;

public interface IPaymentService {
    public CommonResult<Payment> createPayment(Payment payment);

    public CommonResult<Payment> selectPaymentById(Long id);
}
