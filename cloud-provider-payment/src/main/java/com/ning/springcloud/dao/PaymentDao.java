package com.ning.springcloud.dao;

import com.ning.springcloud.entities.dao.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: nicholas
 * @Date: 2020/7/12 16:36
 * @Descreption:
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
