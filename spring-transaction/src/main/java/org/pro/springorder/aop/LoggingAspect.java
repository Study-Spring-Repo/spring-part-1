package org.pro.springorder.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("org.pro.springorder.aop.CommonPointcut.repositoryMethodPointcut()")
    public void servicePublicKMethodPointcut() {};

    @Around("servicePublicKMethodPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before method called. {}", joinPoint.getSignature().toString());
        var result = joinPoint.proceed();
        log.info("After method called with result => {}", result);
        return result;
    }
}
