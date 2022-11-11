package hu.pte.inventory_management_system.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RestControllerAspect {

    @Pointcut("within(hu.pte.inventory_management_system.controllers.*)")
    public void restControllerPointcut(){
    }

    @Before("restControllerPointcut()")
    public void logBefore(JoinPoint jp){
        MethodSignature signature = (MethodSignature) jp.getSignature();
        log.info("full method description: " + signature.getMethod());
        log.info("method name: " + signature.getMethod().getName());
        log.info("declaring type: " + signature.getDeclaringType());
        log.info("returning type: " + signature.getReturnType());
        log.info("Parameters: " + Arrays.toString(signature.getParameterNames()));
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
