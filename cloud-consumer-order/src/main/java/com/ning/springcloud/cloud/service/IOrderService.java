package com.ning.springcloud.cloud.service;

import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.response.CommonResult;

public interface IOrderService {
    CommonResult<Payment> selectPaymentById(Long id);

    String getPaymentImage();

}
