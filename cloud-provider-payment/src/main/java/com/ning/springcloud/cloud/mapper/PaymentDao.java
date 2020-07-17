package com.ning.springcloud.cloud.mapper;

import com.ning.springcloud.api.dto.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 不在能知，乃在能行 ——nicholas
 * @Date: 2020/7/12 16:36
 * @Descreption:
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

    Payment getPaymentByOrderNo(@Param("orderNo") String orderNo);
}
