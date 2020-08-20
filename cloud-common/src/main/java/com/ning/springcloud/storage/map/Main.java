package com.ning.springcloud.storage.map;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @author 不在能知，乃在能行 ——nicholas
 * @description
 * @date 2020/8/20 10:31
 */
public class Main {
    public static void main(String[] args) {
        int testCount = 100000;
        int threadCount = 4;
        System.out.println(testCount + "次测试：");
        int errorCount = 0;
        for (int i = 0; i < testCount; i++) {
            int result = test(threadCount);
            if (result > 1) {
                System.out.println("裂开第" + ++errorCount + "次");
            } else if (result == 0) {
                System.out.println("奇怪");
            }
        }
        System.exit(0);
    }

    private static int test(int threadCount) {
        CopyOnWriteArraySet<com.ning.springcloud.storage.map.Singleton> set = new CopyOnWriteArraySet<>();
        try {
            for (int i = 0; i < threadCount; i++) {
                new Thread(() -> {
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    set.add(com.ning.springcloud.storage.map.Singleton.getInstance());
                }).start();
            }
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return set.size();
    }
}
