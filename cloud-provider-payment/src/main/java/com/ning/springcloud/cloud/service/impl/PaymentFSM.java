package com.ning.springcloud.cloud.service.impl;

import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.api.dto.Transaction;
import com.ning.springcloud.cloud.service.IPaymentFSM;

/**
 * @Description 订单 流水 的状态机。（所有状态改变的动作的实现。）
 * @Date 2020/7/13 17:22
 * @Created by 不在能知，乃在能行 ——nicholas
 */
public class PaymentFSM implements IPaymentFSM {

    @Override
    public Payment createPayment() {
        return null;
    }

    @Override
    public Payment completePayment(Payment payment) {
        return null;
    }

    @Override
    public Transaction createTransaction() {
        return null;
    }

    @Override
    public Transaction completeTransaction(Transaction transaction) {
        return null;
    }
}
