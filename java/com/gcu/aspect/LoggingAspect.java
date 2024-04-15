package com.gcu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log entry for all methods in UserController
    @Before("execution(* com.gcu.controller.UserController.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    // Log exit and return values for all methods in UserController
    @AfterReturning(pointcut = "execution(* com.gcu.controller.UserController.*(..))", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        logger.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), result);
    }

    // Log exceptions for all methods in UserController
    @AfterThrowing(pointcut = "execution(* com.gcu.controller.UserController.*(..))", throwing = "e")
    public void logMethodException(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    // Ensure logging happens - method exit path
    @After("execution(* com.gcu.controller.UserController.*(..))")
    public void logMethodFinally(JoinPoint joinPoint) {
        logger.debug("Finally: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName());
    }
}
