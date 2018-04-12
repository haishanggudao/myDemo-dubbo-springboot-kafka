package com.htsc.htbcps.myDemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.htsc.htbcps.myDemo.services.impl..*(..)) and @annotation(com.htsc.htbcps.myDemo.annotation.Log)")
    private void cut() {
    }

    @Around("cut()")
    public Object advice(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕通知之开始");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("环绕通知之结束");
        return result;
    }

}
