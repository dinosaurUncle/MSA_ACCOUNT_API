package me.dinosauruncle.service.portal.aop;

import me.dinosauruncle.service.portal.service.DefaultService;
import me.dinosauruncle.service.portal.nonaopservice.EventMessageService;
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

    @Before("execution(* me.dinosauruncle.service.portal.service.*.*(..))")
    public Object parameterMapClean(JoinPoint joinPoint) throws Throwable{
        List<Object> argsList =  Arrays.asList(joinPoint.getArgs());
        Map<String, String> argsMap = new HashMap<String, String>();
        if (!argsList.isEmpty()) {
            argsList.forEach(arg ->{
                if (arg instanceof String[]){
                    String[] stringArray = (String[]) arg;
                    for (String string : stringArray){
                        if (string.indexOf("accountId:") != -1){
                            argsMap.put("accountId",string.substring("accountId:".length()));
                        } else if (string.indexOf("roleId:") != -1) {
                            argsMap.put("roleId",string.substring("roleId:".length()));
                        } else if (string.indexOf("targetAccountId:") != -1) {
                            argsMap.put("targetAccountId",string.substring("targetAccountId:".length()));
                        } else if (string.indexOf("pageName:") != -1) {
                            argsMap.put("pageName",string.substring("pageName:".length()));
                        }
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
            map.clear();
        } else {
            map = new HashMap<String, Object>();
        }
        if (argsMap.containsKey("accountId")) map.put("accountId", argsMap.get("accountId"));
        if (argsMap.containsKey("targetAccountId")) map.put("targetAccountId", argsMap.get("targetAccountId"));
        if (argsMap.containsKey("roleId")) map.put("roleId", argsMap.get("roleId"));
        if (argsMap.containsKey("pageName")) map.put("pageName", argsMap.get("pageName"));
        retVal.setParameterMap(map);
        return retVal;
    }

    @After("execution(* me.dinosauruncle.service.portal.service.*.*(..))")
    public Object apiCallLogPrint(JoinPoint joinPoint) throws Throwable{
        logger.info("AccountService end function: " + joinPoint.getSignature().getName());
        DefaultService retVal = null;
        retVal = (DefaultService) joinPoint.getTarget();
        eventMessageService.aopSave(joinPoint, retVal.getParameterMap());
        logger.info("return value: " + retVal.getParameterMap().toString());

        return retVal;
    }
}
