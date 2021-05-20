package com.heilig.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
@Aspect
@Component
@Slf4j
@ConditionalOnExpression("${logging.aspect.enabled:true}")
public class LoggingAspect {

    /**
     * Take a method annotated by having {@link com.heilig.demo.annotation.LogExecutionTime} and compute the execution time of that method.
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.heilig.demo.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        // Measure method execution time
        StopWatch stopWatch = new StopWatch(className + " -> " + methodName);
        stopWatch.start();
        var result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return result;
    }
}
