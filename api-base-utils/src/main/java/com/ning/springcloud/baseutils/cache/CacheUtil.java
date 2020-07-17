package com.ning.springcloud.baseutils.cache;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
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

    /**
     * 获取redis中存储方法结果的key。
     *
     * @param method
     * @param args
     * @return
     */
    private String getCacheKey(Method method, Object... args) {
        EnableCache annotation = method.getAnnotation(EnableCache.class);
        String key = annotation.key();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(key)) {
            String methodName = method.getName();
            String className = method.getDeclaringClass().getName();
            int hashCode = JSON.toJSONString(args).hashCode();
            sb.append(className).append(".").append(methodName).append(":").append(hashCode);
            return sb.toString();
        }
        String[] splitKey = key.split("%");
        sb.append(splitKey[0]);
        for (int i = 1; i < splitKey.length; i++) {
            sb.append(args[Integer.parseInt(splitKey[i])]);
        }
        return sb.toString();
    }

    public void putCache(String value, Method method, Object... args) {
        EnableCache annotation = method.getAnnotation(EnableCache.class);
        String key = getCacheKey(method, args);
        long expireTime = annotation.expireTime();
        TimeUnit timeunit = annotation.timeunit();
        if (expireTime > 0) {
            redisTemplate.opsForValue().set(key, value, expireTime, timeunit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    public Object getCache(Method method, Object... args) {
        String key = getCacheKey(method, args);
        String cache = redisTemplate.opsForValue().get(key);
        Object result = null;
        if (StringUtils.isNotEmpty(cache)) {
            Class<?> returnType = method.getReturnType();
            result = JSON.parseObject(cache, returnType);
        }
        return result;
    }

}
