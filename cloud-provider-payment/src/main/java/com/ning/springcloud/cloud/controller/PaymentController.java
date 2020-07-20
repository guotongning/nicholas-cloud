package com.ning.springcloud.cloud.controller;

import com.ning.springcloud.api.interfaces.IPaymentService;
import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.response.CommonResult;
import com.ning.springcloud.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 不在能知，乃在能行 ——nicholas
 * @Date: 2020/7/12 16:55
 * @Descreption:
 */
@RestController
@Slf4j
public class PaymentController implements IPaymentService {

    @Resource
    private PaymentService paymentService;

    @Override
    public CommonResult<Payment> createPayment(@RequestBody Payment payment) {
        if (payment == null || !payment.check()) {
            return new CommonResult<>(-1, "参数不合法", null);
        }
        int result = paymentService.create(payment);
        if (result > 0) {
            log.info("insert payment success payment={}", payment);
            return new CommonResult<>(200, "success", payment);
        } else {
            log.info("insert payment fail");
            return new CommonResult<>(-1, "fail", null);
        }
    }

    @Override
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<>(200, "success", payment);
        } else {
            return new CommonResult<>(-1, "fail", null);
        }
    }

    @Override
    public CommonResult<Payment> selectPaymentByOrderNo(@PathVariable("orderNo") String orderNo) {
        Payment payment = paymentService.getPaymentByOrderNo(orderNo);
        if (payment != null) {
            return new CommonResult<>(200, "success", payment);
        } else {
            return new CommonResult<>(-1, "fail", null);
        }
    }

}
