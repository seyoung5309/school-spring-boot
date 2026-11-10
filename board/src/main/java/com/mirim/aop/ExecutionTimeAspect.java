package com.mirim.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("execution(* com.mirim.board.*Service.*(..))")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long time = System.currentTimeMillis() - start;
            System.out.println(
                    "실행 시간: " + joinPoint.getSignature().toShortString()
                            + " " + time + "ms"
            );
        }
    }
}