package com.ning.springcloud.cloud.service.impl;

import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.baseutils.cache.EnableCache;
import com.ning.springcloud.baseutils.log.InvocationDetail;
import com.ning.springcloud.cloud.mapper.PaymentDao;
import com.ning.springcloud.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 不在能知，乃在能行 ——nicholas
 * @Date: 2020/7/12 16:52
 * @Descreption:
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    @EnableCache(printParameter = true, printReturnValue = true)
    @InvocationDetail(methodName = "根据ID获取订单", printMethodParameters = false, printInvokeTime = true, printRequestSerial = true)
    public Payment getPaymentById(Long id) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentDao.getPaymentById(id);
    }

    @Override
    @EnableCache
    public Payment getPaymentByOrderNo(String orderNo) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentDao.getPaymentByOrderNo(orderNo);
    }
}
