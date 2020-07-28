package com.ning.springcloud.baseutils.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @description
 * @date 2020/7/23 14:34
 * @author 不在能知，乃在能行 ——nicholas
 */
@Aspect
@Slf4j
@Component
@Order(1)
public class PrintInvocationDetailAop {
    @Value("${spring.profiles.active}")
    private String active;

    @Pointcut("@annotation(com.ning.springcloud.baseutils.log.InvocationDetail)")
    public void invocationDetail() {
    }

    @Around(value = "invocationDetail()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            InvocationDetail annotation = method.getAnnotation(InvocationDetail.class);
            if (annotation.printOnlineLog() || !"online".equals(active)) {
                String requestSerial = "";
                if (annotation.printRequestSerial()) {
                    requestSerial = UUID.randomUUID().toString().replaceAll("-", "");
                }
                String methodName = annotation.methodName();
                if (StringUtils.isEmpty(methodName)) {
                    methodName = getMethodFullName(method);
                }
                log.info("invoke method name : {} : {}", methodName, requestSerial);
                Object[] args = joinPoint.getArgs();
                if (annotation.printMethodParameters()) {
                    log.info("invoke method parameters : {} : {}", JSONObject.toJSONString(args), requestSerial);
                }
                result = joinPoint.proceed();
                long end = System.currentTimeMillis();
                if (annotation.printReturnValue()) {
                    log.info("invoke method return : {} : {}", JSON.toJSONString(result), requestSerial);
                } else if (annotation.printReturnValueFormatted()) {
                    String jsonResult = JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                            SerializerFeature.WriteDateUseDateFormat);
                    log.info("invoke method return : {} : {}", jsonResult, requestSerial);
                }
                if (annotation.printInvokeTime()) {
                    log.info("invoke method time : {}ms : {}", end - start, requestSerial);
                }
            } else {
                return joinPoint.proceed();
            }
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }
        return result;
    }

    private String getMethodFullName(Method method) {
        StringBuilder sb = new StringBuilder();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        sb.append(className).append(".").append(methodName);
        return sb.toString();
    }
}
