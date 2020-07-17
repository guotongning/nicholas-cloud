package com.ning.springcloud.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author: 不在能知，乃在能行 ——nicholas
 * @Date: 2020/7/12 16:30
 * @Descreption:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Payment {
    private Long id;
    /**
     * 订单流水号
     * json array
     */
    private String serials;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单提交人
     */
    private Long submitterId;
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 支付类型
     */
    private Integer paymentType;
    /**
     * 支付附属
     * 比如使用了优惠券的情况，可以根据此查询详细信息。
     */
    private String paymentAttachment;
    /**
     * 收货人信息Id
     */
    private Long receiverId;
    /**
     * 订单审核人Id
     */
    private Long reviewerId;
    /**
     * 订单金额（实际支付金额）
     */
    private Long orderAmount;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 最后一次修改时间
     */
    private Long updateTime;

    /**
     * 判断是否缺少参数
     * @return 参数合法返回true，否则false
     */
    public boolean check() {
        return true;
    }
}
