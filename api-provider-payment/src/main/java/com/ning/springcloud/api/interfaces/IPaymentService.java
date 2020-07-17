package com.ning.springcloud.api.interfaces;


import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.response.CommonResult;

public interface IPaymentService {
    CommonResult<Payment> createPayment(Payment payment);

    CommonResult<Payment> selectPaymentById(Long id);

    CommonResult<Payment> selectPaymentByOrderNo(String orderNo);
}
