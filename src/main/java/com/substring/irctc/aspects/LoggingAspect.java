package com.substring.irctc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    private Logger logger= LoggerFactory.getLogger(LoggingAspect.class);
//    cross cutting concern logic
    @Around("execution(* com.substring.irctc.service.impl.*.*(..))")
    public Object logBeforeMethod(ProceedingJoinPoint proceedingjoinPoint)throws Throwable
    {
         logger.info("Before Method Execution {}",proceedingjoinPoint.getSignature().getName());
        Object result= proceedingjoinPoint.proceed();

         logger.info("After Method Execution {}",proceedingjoinPoint.getSignature().getName());
         return result;
    }
}
