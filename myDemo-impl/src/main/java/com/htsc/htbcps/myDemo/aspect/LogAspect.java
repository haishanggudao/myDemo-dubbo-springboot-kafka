package com.htsc.htbcps.myDemo.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.htsc.htbcps.myDemo.services.impl..*(..)) and @annotation(com.htsc.htbcps.myDemo.annotation.Log)")
    private void cut() {
    }

    @Around("cut()")
    public Object advice(ProceedingJoinPoint joinPoint) {
        Long beginTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info(MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName() + result + " in ["
                + String.valueOf(System.currentTimeMillis() - beginTime) + "]ms");
        return result;
    }

}
