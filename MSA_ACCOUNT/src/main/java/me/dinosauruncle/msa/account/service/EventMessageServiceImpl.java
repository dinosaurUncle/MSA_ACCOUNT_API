package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.domain.EventMessage;
import me.dinosauruncle.msa.account.repository.EventMessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EventMessageServiceImpl extends EventMessageService {

    private static Logger logger = LogManager.getLogger();
    @Autowired
    EventMessageRepository eventMessageRepository;

    @Override
    public EventMessage save(JoinPoint joinPoint, Map<String, Object> parameterMap) {
        String accountId = null;
        if (parameterMap.containsKey("accountId")){
            accountId = String.valueOf(parameterMap.get("accountId"));
        } else {
            if (parameterMap.containsKey("account")){
                Account account = (Account)parameterMap.get("account");
                accountId = account.getAccountId();
            } else {
                accountId ="";
            }
        }
        EventMessage eventMessage = new EventMessage();
        eventMessage.setCheck(false);
        eventMessage.setMessage(getEventMessageContent(
                joinPoint.getSignature().getDeclaringType().getName(), joinPoint.getSignature().getName(), parameterMap));
        eventMessage.setAccountId(accountId);
        eventMessage.setDate(new Date());
        if (StringUtils.isNotEmpty(eventMessage.getMessage())){
            try {
                eventMessageRepository.save(eventMessage);
            } catch (Exception e){
                logger.error(e.getMessage());
                eventMessage = null;
            }
        } else {
            eventMessage = null;
        }
        return eventMessage;
    }

    @Override
    public List<EventMessage> getEventMessageList(String accountId) {
        return null;
    }

    @Override
    public void update(List<String> eventMessageList) {

    }

    @Override
    public String getEventMessageContent(String serviceType, String methodName, Map<String, Object> parameterMap) {
        String returnMessage = "";
        String subStringServiceType = serviceType.substring("me.dinosauruncle.msa.account.service.".length());
            switch (subStringServiceType){
                case  "AccountMappingRoleServiceImpl" :
                    break;
                case  "AccountServiceImpl" :
                    switch (methodName) {
                        case "save" :
                            returnMessage = "'" + ((Account)parameterMap.get("account")).getAccountId() + "'님 회원 가입되었습니다";
                            break;
                        case "update" :
                            returnMessage = "'" + ((Account)parameterMap.get("account")).getAccountId() + "'님의 계정정보가 수정되었습니다";
                            break;
                    }
                    break;
                case  "PageServiceImpl" :
                    break;
                case  "RoleMappingPageServiceImpl" :
                    break;
                case  "RoleServiceImpl" :
                    break;

            }
        return returnMessage;
    }
}
