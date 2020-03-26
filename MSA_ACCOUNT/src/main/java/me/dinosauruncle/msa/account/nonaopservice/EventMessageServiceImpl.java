package me.dinosauruncle.msa.account.nonaopservice;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.domain.EventMessage;
import me.dinosauruncle.msa.account.repository.EventMessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EventMessageServiceImpl extends EventMessageService {

    private static Logger logger = LogManager.getLogger();
    @Autowired
    EventMessageRepository eventMessageRepository;

    @Override
    public EventMessage aopSave(JoinPoint joinPoint, Map<String, Object> parameterMap) {
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
        return save(joinPoint.getSignature().getDeclaringType().getName(), joinPoint.getSignature().getName(), accountId);
    }

    @Override
    public EventMessage save(String serviceType, String methodName, String accountId) {
        EventMessage eventMessage = new EventMessage();
        eventMessage.setCheck(false);
        eventMessage.setEventMessageDescription(getEventMessageContent(
                serviceType, methodName, accountId));
        eventMessage.setAccountId(accountId);
        eventMessage.setDate(new Date());
        if (StringUtils.isNotEmpty(eventMessage.getEventMessageDescription())){
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
    public List<EventMessage> getEventMessageListFillterByDate(String accountId) {
        List<String> tempDateStmpList = new ArrayList<String>();
        List<EventMessage> eventMessages = new ArrayList<EventMessage>();
        eventMessageRepository.selectByAccountId(accountId).stream().forEach(eventMessage -> {
            LocalDateTime date = eventMessage.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (tempDateStmpList.size() == 0){
                tempDateStmpList.add(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                eventMessages.add(eventMessage);
            } else {
                tempDateStmpList.forEach(tempDate -> {
                    if (tempDate.equals(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
                        eventMessage.setDate(null);
                        eventMessages.add(eventMessage);
                    }
                    else{
                        tempDateStmpList.add(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        eventMessages.add(eventMessage);
                    }
                });
            }
        });

        return eventMessages;
    }

    @Override
    public List<EventMessage> getEventMessageList() {
        return eventMessageRepository.findAll();
    }

    @Override
    public void isCheckChangeUpdate(List<String> eventMessageIdList) {
        eventMessageIdList.stream().forEach(eventMessageId -> {
            EventMessage eventMessage = eventMessageRepository.findById(Long.valueOf(eventMessageId)).get();
            eventMessage.setCheck(true);
            eventMessageRepository.save(eventMessage);
        });
    }

    @Override
    public String getEventMessageContent(String serviceType, String methodName, String accountId) {
        String returnMessage = "";
        String subStringServiceType = serviceType.substring("me.dinosauruncle.msa.account.service.".length());
            switch (subStringServiceType){
                case  "AccountMappingRoleServiceImpl" :
                    break;
                case  "AccountServiceImpl" :
                    switch (methodName) {
                        case "save" :
                            returnMessage = "'" + accountId + "'님 회원 가입되었습니다";
                            break;
                        case "update" :
                            returnMessage = "'" + accountId + "'님의 계정정보가 수정되었습니다";
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

    @Override
    public int nonCheckedCount(String accountId) {
        return eventMessageRepository.nonCheckedCount(accountId);
    }

    @Override
    public Map<String, Object> getEventMessagesInfo(String accountId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("eventMessages", getEventMessageListFillterByDate(accountId));
        result.put("count", nonCheckedCount(accountId));
        return result;
    }

    private List<String> eventMessageLogicalPartitionResult(String serviceType,
                                                            String methodName, String accountId){
        List<String> resultList = new ArrayList<String>();
        String subStringServiceType = serviceType.substring("me.dinosauruncle.msa.account.service.".length());
        switch (subStringServiceType){
            case  "AccountMappingRoleServiceImpl" :
                resultList.add("권한지정");
                break;
            case  "AccountServiceImpl" :
                resultList.add("account");
                switch (methodName) {
                    case "save" :
                        resultList.add("회원가입");
                        resultList.add("'" + accountId + "'님 회원 가입되었습니다");
                        break;
                    case "update" :
                        resultList.add("계정정보수정");
                        resultList.add("'" + accountId + "'님 회원 가입되었습니다");
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
        return resultList;
    }
}
