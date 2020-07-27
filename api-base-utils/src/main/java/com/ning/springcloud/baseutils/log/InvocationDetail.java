package com.ning.springcloud.baseutils.log;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvocationDetail {
    String methodName() default "";

    boolean printMethodParameters() default true;

    boolean printReturnValue() default false;

    boolean printReturnValueFormatted() default false;

    boolean printInvokeTime() default false;

    boolean printOnlineLog() default false;

    boolean printRequestSerial() default false;
}
