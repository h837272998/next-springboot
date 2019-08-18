package com.hjh.myspringboot.myspringboot.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 20:06
 */
@Slf4j
@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.hjh.myspringboot.myspringboot.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        log.info("Time Aspect Start");
        //获取传输args
        Object[] args = pjp.getArgs();
        for (Object arg:args){
            log.info("arg is:" +arg);
        }
        long start = System.currentTimeMillis();
        Object o = pjp.proceed();
        log.info("Time Aspect spent:"+(new Date().getTime()-start));
        log.info("Time Aspect end");
        return o;
    }
}
