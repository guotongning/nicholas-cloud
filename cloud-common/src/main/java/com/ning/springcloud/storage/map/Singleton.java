package com.ning.springcloud.storage.map;

/**
 * @author 不在能知，乃在能行 ——nicholas
 * @description
 * @date 2020/8/20 15:42
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
