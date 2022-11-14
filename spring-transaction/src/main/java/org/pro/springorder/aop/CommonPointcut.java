package org.pro.springorder.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

    @Pointcut("execution(public * org.pro.springorder..*Service.*(..))")
    public void servicePublicMethodPointcut() {
    }

    @Pointcut("execution(public * org.pro.springorder..*Repository.insert(..))")
    public void repositoryMethodPointcut() {
    }
}
