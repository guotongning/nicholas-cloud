package com.ning.springcloud.cloud.controller;

import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.cloud.feign.PaymentService;
import com.ning.springcloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: nicholas
 * @Date: 2020/7/14 19:38
 * @Descreption:
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/get/id/{id}")
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id) {
        return paymentService.selectPaymentById(id);
    }
}
