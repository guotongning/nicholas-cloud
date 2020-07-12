package com.ning.springcloud.service.impl;

import com.ning.springcloud.basetest.BaseTest;
import com.ning.springcloud.entities.dao.Payment;
import com.ning.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PaymentServiceImplTest extends BaseTest {

    @Resource
    private PaymentService paymentService;

    @Test
    public void create() {
        Payment payment = new Payment(1L, "12345678910");
        int result = paymentService.create(payment);
        log.info("insert {}", result > 0 ? "success" : "fail");
    }

    @Test
    public void getPaymentById() {
        Payment payment = paymentService.getPaymentById(1L);
        log.info("payment={}", payment);
    }
}