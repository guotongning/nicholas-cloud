package com.ning.springcloud.api;

import com.ning.springcloud.common.response.CommonResult;
import com.ning.springcloud.entities.dao.Payment;

public interface IPaymentService {
    public CommonResult<Payment> createPayment(Payment payment);

    public CommonResult<Payment> selectPaymentById(Long id);
}
