package com.ning.springcloud.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description
 * @date 2020/7/16 16:29
 * @created by 不在能知，乃在能行 ——nicholas
 */
@Aspect
@Slf4j
@Component
public class EnableCacheAop {

    @Autowired
    private CacheUtil cacheUtil;

    @Pointcut("@annotation(com.ning.springcloud.cache.EnableCache)")
    public void enableCache() {
    }

    @Around(value = "enableCache()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Object[] args = joinPoint.getArgs();
            String cache = cacheUtil.getCache(method, args);
            if (StringUtils.isNotEmpty(cache)) {
                Class<?> returnType = method.getReturnType();
                EnableCache annotation = method.getAnnotation(EnableCache.class);
                if (annotation.printResult()) {
                    log.info("cache aop : method {} return cache value : {}", method.getName(), cache);
                }
                return JSON.parseObject(cache, returnType);
            }
            result = joinPoint.proceed();
            if (result != null) {
                String jsonResult = JSON.toJSONString(result);
                cacheUtil.putCache(jsonResult, method, joinPoint.getArgs());
                log.info("cache aop : method {} result cache success : {}", method.getName(), jsonResult);
            }
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
