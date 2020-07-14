package com.ning.springcloud.controller;

import com.ning.springcloud.entities.dao.Payment;
import com.ning.springcloud.entities.response.CommonResult;
import com.ning.springcloud.service.PaymentService;
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
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;


    @PutMapping(value = "/create")
    public CommonResult<Payment> createPayment(Payment payment) {
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

    @GetMapping(value = "/select/{id}")
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<>(200, "success", payment);
        } else {
            return new CommonResult<>(-1, "fail", null);
        }
    }

}
