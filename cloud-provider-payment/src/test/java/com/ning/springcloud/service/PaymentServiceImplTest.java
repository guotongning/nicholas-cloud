package com.ning.springcloud.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ning.springcloud.common.test.BaseTest;
import com.ning.springcloud.entities.dao.Payment;
import com.ning.springcloud.utils.NumberUtil;
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
        Payment payment = new Payment();
        long time = System.currentTimeMillis();
        payment.setCreateTime(time)
                .setUpdateTime(time)
                .setOrderAmount(101L)
                .setOrderNo(NumberUtil.getOrderNo())
                .setOrderStatus(1)
                .setOrderType(1)
                .setPaymentAttachment("asdfasdf")
                .setPaymentType(1)
                .setReceiverId(1L)
                .setReviewerId(1L)
                .setSerials("{'123412341235','123412341234','123412341236'}")
                .setSubmitterId(1L);
        int result = paymentService.create(payment);
        log.info("insert {} id={}", result > 0 ? "success" : "fail", payment.getId());
    }

    @Test
    public void getPaymentById() {
        Payment payment = paymentService.getPaymentById(2L);
        log.info("payment={}", JSON.toJSONString(payment, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
    }
}