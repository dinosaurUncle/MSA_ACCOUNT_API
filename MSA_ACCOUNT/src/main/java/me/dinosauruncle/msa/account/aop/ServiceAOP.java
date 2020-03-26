package me.dinosauruncle.msa.account.aop;

import me.dinosauruncle.msa.account.service.DefaultService;
import me.dinosauruncle.msa.account.nonaopservice.EventMessageService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Aspect
public class ServiceAOP {

    private Logger logger = LoggerFactory.getLogger(ServiceAOP.class);

    @Autowired
    EventMessageService eventMessageService;

    @Before("execution(* me.dinosauruncle.msa.account.service.*.*(..))")
    public Object parameterMapClean(JoinPoint joinPoint) throws Throwable{
        List<Object> argsList =  Arrays.asList(joinPoint.getArgs());
        List<String> accountList = new ArrayList<>();
        if (!argsList.isEmpty()) {
            argsList.stream().forEach(object -> {
                if (object instanceof String){
                    String string = String.valueOf(object);
                    if (string.indexOf("accountId:") != -1){
                        accountList.add(string.substring("accountId:".length()));
                    }
                }
            });
        }


        logger.info("AccountService start function: " + joinPoint.getSignature().getName());
        logger.info("AccountService start class: " + joinPoint.getSignature().getDeclaringType().getName());
        DefaultService retVal = null;
        retVal = (DefaultService) joinPoint.getTarget();
        Map<String, Object> map = null;
        if (retVal.getParameterMap() != null){
            map = retVal.getParameterMap();
        } else {
            map = new HashMap<String, Object>();
        }
        if (!accountList.isEmpty()) map.put("accountId", accountList.get(0));
        retVal.setParameterMap(map);
        return retVal;
    }

    @After("execution(* me.dinosauruncle.msa.account.service.*.*(..))")
    public Object apiCallLogPrint(JoinPoint joinPoint) throws Throwable{
        logger.info("AccountService end function: " + joinPoint.getSignature().getName());
        DefaultService retVal = null;
        retVal = (DefaultService) joinPoint.getTarget();
        eventMessageService.aopSave(joinPoint, retVal.getParameterMap());
        logger.info("return value: " + retVal.getParameterMap().toString());

        return retVal;
    }
}
