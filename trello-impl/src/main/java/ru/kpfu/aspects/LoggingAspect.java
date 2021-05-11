package ru.kpfu.aspects;

import org.apache.logging.slf4j.SLF4JLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    @Pointcut("execution(public * ru.kpfu.services.UserServiceImpl.*(..))")
    public void aopUserServicePointcut(){}

    @Pointcut("execution(public * ru.kpfu.services.BoardServiceImpl.*(..))")
    public void aopBoardServicePointcut(){}

    @Before("aopUserServicePointcut()")
    public void beforeAop(){
        logger.info("UserServiceImpl class is starting now");
    }

    @Around("aopBoardServicePointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("BoardServiceImpl class is starting now");
        Object proceed = joinPoint.proceed();
        logger.info("BoardServiceImpl class finished");
        return proceed;
    }
}
