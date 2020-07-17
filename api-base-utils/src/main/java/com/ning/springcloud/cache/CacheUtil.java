package com.ning.springcloud.cache;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @date 2020/7/16 16:36
 * @created by 不在能知，乃在能行 ——nicholas
 */
@Component
public class CacheUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String getCacheKey(Method method, Object[] args) {
        EnableCache annotation = method.getAnnotation(EnableCache.class);
        String key = annotation.key();
        if (StringUtils.isEmpty(key)) {
            StringBuilder sb = new StringBuilder();
            String methodName = method.getName();
            String className = method.getDeclaringClass().getName();
            int hashCode = JSON.toJSONString(args).hashCode();
            sb.append(className).append(".").append(methodName).append(":").append(hashCode);
            key = sb.toString();
        }
        return key;
    }

    public void putCache(String value, Method method, Object... args) {
        EnableCache annotation = method.getAnnotation(EnableCache.class);
        String key = getCacheKey(method, args);
        long expireTime = annotation.expireTime();
        TimeUnit timeunit = annotation.timeunit();
        redisTemplate.opsForValue().set(key, value, expireTime, timeunit);
    }

    public String getCache(Method method, Object... args) {
        String key = getCacheKey(method, args);
        return redisTemplate.opsForValue().get(key);
    }
}
