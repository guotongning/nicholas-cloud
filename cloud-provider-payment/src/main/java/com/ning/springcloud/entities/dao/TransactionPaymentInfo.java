package com.ning.springcloud.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Date 2020/7/13 15:33
 * @Created by 不在能知，乃在能行 ——nicholas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TransactionPaymentInfo {
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 出入账 账号
     */
    private String account;
    /**
     * 用户姓名或昵称
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号
     */
    private String IdentityID;
    /**
     * 地址
     */
    private String address;
    /**
     * 出入账时间
     */
    private String time;
}
