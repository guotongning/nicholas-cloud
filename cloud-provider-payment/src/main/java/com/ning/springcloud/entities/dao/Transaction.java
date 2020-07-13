package com.ning.springcloud.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Date 2020/7/13 14:47
 * @Created by 不在能知，乃在能行 ——nicholas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Transaction {
    private Long id;
    /**
     * 流水号
     */
    private String serial;
    /**
     * 流水类型
     */
    private Integer transactionType;
    /**
     * 流水状态
     */
    private Integer transactionStatus;
    /**
     * 出账方信息
     */
    private TransactionPaymentInfo paymentInfo;
    /**
     * 入账方信息
     */
    private TransactionPaymentInfo collectInfo;
}
