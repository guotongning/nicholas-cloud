package com.ning.springcloud.cloud.service.impl;

import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.cloud.feign.PaymentServiceFeignClient;
import com.ning.springcloud.cloud.service.IOrderService;
import com.ning.springcloud.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author 不在能知，乃在能行 ——nicholas
 * @description
 * @date 2020/8/13 10:27
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;

    @Override
    public CommonResult<Payment> selectPaymentById(Long id) {
        CommonResult<Payment> paymentById = paymentServiceFeignClient.selectPaymentById(id);
        Assert.notNull(paymentById, "");
        return paymentById;
    }

    @Override
    public String getPaymentImage() {
        return null;
    }
}
