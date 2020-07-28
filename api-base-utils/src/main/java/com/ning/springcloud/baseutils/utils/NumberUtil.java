package com.ning.springcloud.baseutils.utils;

import java.util.Random;

/**
 * @Description
 * @Date 2020/7/13 15:03
 * @Created by 不在能知，乃在能行 ——nicholas
 */
public class NumberUtil {

    public static void main(String[] args) {
        System.out.println(getOrderNo());
    }

    public static String getOrderNo() {
        //15位
        long timeMillis = System.nanoTime();
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
