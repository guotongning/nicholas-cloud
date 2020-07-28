package com.ning.springcloud.baseutils.cache;

import com.alibaba.fastjson.JSON;
import com.ning.springcloud.baseutils.log.InvocationDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @description
 * @date 2020/7/16 16:29
 * @author 不在能知，乃在能行 ——nicholas
 */
@Aspect
@Slf4j
@Component
@Order(2)
public class EnableCacheAop {

    @Resource
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
            InvocationDetail detailAnnotation = method.getAnnotation(InvocationDetail.class);
            Object[] args = joinPoint.getArgs();
            if (annotation.printParameter() && (detailAnnotation == null || !detailAnnotation.printMethodParameters())) {
                log.info("cache aop : method {} invoke parameter : {}", method.getName(), JSON.toJSONString(args));
            }
            String cache = cacheUtil.getCache(method, args);
            if (StringUtils.isNotEmpty(cache)) {
                Class<?> returnType = method.getReturnType();
                result = JSON.parseObject(cache, returnType);
            }
            if (annotation.printReturnValue() && (detailAnnotation == null ||
                    (!detailAnnotation.printReturnValue() && !detailAnnotation.printReturnValueFormatted()))) {
                log.info("cache aop : method {} invoke return : {}", method.getName(), cache);
            }
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
