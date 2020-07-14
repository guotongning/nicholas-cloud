package com.ning.springcloud.utils.test;

import com.ning.springcloud.utils.NumberUtil;
import org.junit.Test;

/**
 * @description
 * @date 2020/7/14 15:56
 * @created by 不在能知，乃在能行 ——nicholas
 */
public class NumberUtilsTest {

    @Test
    public void test() {
        String orderNo = NumberUtil.getOrderNo();
        System.out.println(orderNo);
    }
}
