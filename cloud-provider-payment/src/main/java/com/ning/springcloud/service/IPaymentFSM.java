package com.ning.springcloud.service;

import com.ning.springcloud.entities.dao.Payment;
import com.ning.springcloud.entities.dao.Transaction;

/**
 * @see com.ning.springcloud.entities.enums.OrderType
 * @see com.ning.springcloud.entities.enums.OrderStatus
 */
public interface IPaymentFSM {
    public Payment initPayment();

    public Payment completePayment(Payment payment);

    public Transaction initTransaction();

    public Transaction completeTransaction(Transaction transaction);
}
