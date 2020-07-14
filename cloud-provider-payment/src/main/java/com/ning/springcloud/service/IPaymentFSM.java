package com.ning.springcloud.service;

import com.ning.springcloud.entities.dao.Payment;
import com.ning.springcloud.entities.dao.Transaction;

/**
 * 状态流转控制
 *
 * @see com.ning.springcloud.entities.enums.OrderType
 * @see com.ning.springcloud.entities.enums.OrderStatus
 */
public interface IPaymentFSM {
    public Payment createPayment();

    public Payment completePayment(Payment payment);

    public Transaction createTransaction();

    public Transaction completeTransaction(Transaction transaction);
}
