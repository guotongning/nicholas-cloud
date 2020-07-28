package com.ning.springcloud.baseutils.cache;

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
     * key = "" ：redis key = "方法全路径：参数列表json.hashcode()"
     * key = "自定义key:%参数列表index" : redis key = "自定义key:参数列表对应下表的参数值"
     *
     * @return
     * @see CacheUtil
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
    long expireTime() default 60L;

    /**
     * 是否在日志中打印方法参数
     *
     * @return
     * @see EnableCacheAop
     */
    boolean printParameter() default false;

    /**
     * 是否在日志中打印方法结果
     *
     * @return
     * @see EnableCacheAop
     */
    boolean printReturnValue() default false;

}
