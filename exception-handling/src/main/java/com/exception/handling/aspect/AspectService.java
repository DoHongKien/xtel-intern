package com.exception.handling.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class AspectService {
    private final Logger logger = LoggerFactory.getLogger(AspectService.class);

    @Before(value = "execution(* com.exception.handling.controller.EmployeeController.*(..))")
    public void beforeExecuteEmployeeController(JoinPoint joinPoint) {
        logger.info("Starting method {} - run time {}", joinPoint.getSignature(), new Date());
    }

    @After(value = "execution(* com.exception.handling.controller.EmployeeController.*(..))")
    public void afterExecuteEmployeeController(JoinPoint joinPoint) {
        logger.info("Ended method {} - run time {}", joinPoint.getSignature(), new Date());
    }

    @AfterThrowing(value = "execution(* com.exception.handling.controller.EmployeeController.*(..))")
    public void afterExecuteEmployeeControllerThrowing(JoinPoint joinPoint) {
        logger.error("Throwing method {} - run time {}", joinPoint.getSignature(), new Date());
    }
}
