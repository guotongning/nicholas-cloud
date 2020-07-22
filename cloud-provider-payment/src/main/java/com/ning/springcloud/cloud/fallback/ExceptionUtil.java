package com.ning.springcloud.cloud.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @description
 * @date 2020/7/22 15:55
 * @created by 不在能知，乃在能行 ——nicholas
 */
public class ExceptionUtil {
    public static String handleException(BlockException ex) {
        return "顶不住了啊....";
    }
}
