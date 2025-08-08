package com.example.quizapp.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.quizapp.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("START: {} at {} | Args: {}", 
            joinPoint.getSignature(), LocalDateTime.now(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.example.quizapp.controller.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("END: {} at {} | Response: {}", 
            joinPoint.getSignature(), LocalDateTime.now(), result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.quizapp.controller.*.*(..))", throwing = "error")
    public void logException(JoinPoint joinPoint, Throwable error) {
        logger.error("EXCEPTION in {} at {} | Message: {}", 
            joinPoint.getSignature(), LocalDateTime.now(), error.getMessage());
    }
}
