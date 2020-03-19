package me.dinosauruncle.msa.account.aop;

import me.dinosauruncle.msa.account.service.DefaultService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAOP {

    private Logger logger = LoggerFactory.getLogger(ServiceAOP.class);

    @Before("execution(* me.dinosauruncle.msa.account.service.*.*(..))")
    public Object parameterMapClean(JoinPoint joinPoint) throws Throwable{
        logger.info("AccountService start function: " + joinPoint.getSignature().getName());
        logger.info("AccountService start class: " + joinPoint.getSignature().getDeclaringType().getName());
        DefaultService retVal = (DefaultService) joinPoint.getTarget();
        retVal.getParameterMap().clear();
        return retVal;
    }

    @After("execution(* me.dinosauruncle.msa.account.service.*.*(..))")
    public Object apiCallLogPrint(JoinPoint joinPoint) throws Throwable{
        logger.info("AccountService end function: " + joinPoint.getSignature().getName());
        DefaultService retVal = (DefaultService) joinPoint.getTarget();
        logger.info("return value: " + retVal.getParameterMap().toString());
        return retVal;
    }
}
