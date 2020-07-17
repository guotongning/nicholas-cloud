package com.ning.springcloud.baseutils.cache;

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

import static com.sun.corba.se.impl.util.RepositoryId.cache;

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

    @Pointcut("@annotation(com.ning.springcloud.baseutils.cache.EnableCache)")
    public void enableCache() {
    }

    @Around(value = "enableCache()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            EnableCache annotation = method.getAnnotation(EnableCache.class);
            Object[] args = joinPoint.getArgs();
            if (annotation.printResult()) {
                log.info("cache aop : method {} invoke parameter : {}", method.getName(), JSON.toJSONString(args));
            }
            if (annotation.printResult()) {
                log.info("cache aop : method {} cached value return : {}", method.getName(), cache);
            }
            result = cacheUtil.getCache(method, args);
            if (result != null) {
                return result;
            }
            result = joinPoint.proceed();
            if (result != null) {
                String jsonResult = JSON.toJSONString(result);
                cacheUtil.putCache(jsonResult, method, joinPoint.getArgs());
                log.info("cache aop : method {} result cache success : {}", method.getName(), jsonResult);
            }
            return result;
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }
        return result;
    }
}
