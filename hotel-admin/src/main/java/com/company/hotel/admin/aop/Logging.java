package com.company.hotel.admin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class Logging implements Ordered {
    @Pointcut("execution(public * com.company.hotel.admin.service.*.*(..))")
    public void  log(){

    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

    }
    @After("log()")
    public void doAfter(JoinPoint jp){

    }

    @AfterReturning(pointcut = "log()",returning="retVal")
    public void doAfterRetuening(JoinPoint jp,Object retVal){
        log.info("返回值："+retVal);
    }
    @AfterThrowing(pointcut = "log()",throwing = "ex")
    public void doAfterThrowing(JoinPoint jp,Exception ex){
        log.info("报错信息："+ex);
    }

    @Around("log()")
    public Object doRound(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
