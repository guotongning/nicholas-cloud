package com.ning.springcloud.utils;

import java.util.Random;

/**
 * @Description
 * @Date 2020/7/13 15:03
 * @Created by nicholas
 */
public class NumberUtil {

    public static String getOrderNo() {
        //14位
        long timeMillis = System.currentTimeMillis();
        //6位
        String randomNumber = getRandomNumberByLength(6);
        return timeMillis + randomNumber;
    }

    public static String getRandomNumberByLength(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }
}
