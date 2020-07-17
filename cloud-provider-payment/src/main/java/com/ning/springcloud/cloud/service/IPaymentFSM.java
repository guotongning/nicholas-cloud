package com.ning.springcloud.cloud.service;


import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.api.dto.Transaction;

/**
 * 状态流转控制
 *
 * @see com.ning.springcloud.cloud.entities.enums.OrderType
 * @see com.ning.springcloud.cloud.entities.enums.OrderStatus
 */
public interface IPaymentFSM {
    public Payment createPayment();

    public Payment completePayment(Payment payment);

    public Transaction createTransaction();

    public Transaction completeTransaction(Transaction transaction);
}
