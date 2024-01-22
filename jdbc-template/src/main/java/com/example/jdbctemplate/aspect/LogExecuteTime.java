package com.example.jdbctemplate.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogExecuteTime {

    @Around(value = "execution(* com.example.jdbctemplate.service.impl.StudentJdbcClientServiceImpl.*(..))")
    public Object beforeExecuteServiceJdbcClient(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long end = System.nanoTime();
        log.info("Executed time by jdbcClient-" + joinPoint.getSignature().getName() + ": " + (end - start));
        return proceed;
    }

    @Around(value = "execution(* com.example.jdbctemplate.service.impl.StudentJdbcTemplateServiceImpl.*(..))")
    public Object beforeExecuteServiceJdbcTemplate(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long end = System.nanoTime();
        log.info("Executed time by jdbcTemplate-" + joinPoint.getSignature().getName() + ": " + (end - start));
        return proceed;
    }
}
