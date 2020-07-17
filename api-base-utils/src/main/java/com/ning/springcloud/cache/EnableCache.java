package com.ning.springcloud.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableCache {
    /**
     * cache key
     *
     * @return
     */
    String key() default "";

    /**
     * 过期时间单位
     *
     * @return
     */
    TimeUnit timeunit() default TimeUnit.SECONDS;

    /**
     * 过期时间数值
     *
     * @return
     */
    long expireTime() default 300L;

    /**
     * 是否在日志中打印方法结果
     * @see EnableCacheAop
     * @return
     */
    boolean printResult() default false;

}
