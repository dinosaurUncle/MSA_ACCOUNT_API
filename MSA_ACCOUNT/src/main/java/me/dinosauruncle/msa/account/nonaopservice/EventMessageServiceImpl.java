package me.dinosauruncle.msa.account.nonaopservice;

import me.dinosauruncle.msa.account.domain.*;
import me.dinosauruncle.msa.account.repository.AccountMappingRoleRepository;
import me.dinosauruncle.msa.account.repository.EventMessageRepository;
import me.dinosauruncle.msa.account.repository.PageRepository;
import me.dinosauruncle.msa.account.repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PageRepository pageRepository;

    @Autowired
    AccountMappingRoleRepository accountMappingRoleRepository;

    @Override
    public List<EventMessage> aopSave(JoinPoint joinPoint, Map<String, Object> parameterMap) {
        String accountId = null;
        if (parameterMap.containsKey("accountId")){
            accountId = String.valueOf(parameterMap.get("accountId"));
        } else {
            if (parameterMap.containsKey("account")){
                Account account = (Account)parameterMap.get("account");
                accountId = account.getAccountId();
                parameterMap.put("accountId", accountId);
            } else {
                accountId ="";
                parameterMap.put("accountId", accountId);
            }
        }
        return save(joinPoint.getSignature().getDeclaringType().getName(), joinPoint.getSignature().getName(), parameterMap);
    }

    @Override
    public List<EventMessage> save(String serviceType, String methodName, Map<String, Object> parameterMap) {

        List<String> resultFieldList = eventMessageLogicalPartitionResult(serviceType, methodName, parameterMap);
        if (resultFieldList.size() == 0) return null;

        String resultServiceType = resultFieldList.get(0);
        List<EventMessage> returnEventMessages = new ArrayList<EventMessage>();
        switch (resultServiceType) {
            case "account&role" :
                returnEventMessages.add(eachSave(resultFieldList, getParameterMapValue(parameterMap, "targetAccountId")));
                break;
            case "role&page" :
                //getParameterMapValue(parameterMap, "roleId")
                List<AccountMappingRole> accountMappingRoles =
                        accountMappingRoleRepository.selectByRoleId(getParameterMapValue(parameterMap, "roleId"));
                accountMappingRoles.stream().forEach(accountMappingRole -> {
                    returnEventMessages.add(eachSave(resultFieldList, accountMappingRole.getAccount().getAccountId()));
                });
                break;
            default:
                returnEventMessages.add(eachSave(resultFieldList, getParameterMapValue(parameterMap, "accountId")));
                break;

        }
        return returnEventMessages;

    }

    private EventMessage eachSave(List<String> resultFieldList, String accountId){
        EventMessage eventMessage = null;
        if (resultFieldList.size() == 3) {
            eventMessage = new EventMessage();
            eventMessage.setCheck(false);
            eventMessage.setAccountId(accountId);
            eventMessage.setEventMessageType(resultFieldList.get(0));
            eventMessage.setEventMessageTitle(resultFieldList.get(1));
            eventMessage.setEventMessageDescription(resultFieldList.get(2));
            eventMessage.setDate(new Date());
            eventMessageRepository.save(eventMessage);
        } else {
            logger.error("resultFieldList: " + resultFieldList);
            logger.error("resultFieldList 안에 3개의 결과값이 존재해야 합니다 ");
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
                                                            String methodName, Map<String, Object> parameterMap){
        List<String> resultList = new ArrayList<String>();
        String subStringServiceType = serviceType.substring("me.dinosauruncle.msa.account.service.".length());
        Role role = null;
        switch (subStringServiceType){
            case  "AccountMappingRoleServiceImpl" :
                if (StringUtils.isNotEmpty(getParameterMapValue(parameterMap, "roleId"))) break;
                resultList.add("account&role");
                role = roleRepository.findById(getParameterMapValue(parameterMap, "roleId")).get();
                switch (methodName) {
                    case "save" :
                        resultList.add("권한부여");
                        resultList.add("'" + getParameterMapValue(parameterMap, "targetAccountId") + "'님에게 권한 ["
                        + role.getRoleName() + "]가 부여 되었습니다");
                        break;
                    case  "delete" :
                        resultList.add("권한삭제");
                        resultList.add("'" + getParameterMapValue(parameterMap, "targetAccountId") + "'님의 권한 ["
                                + role.getRoleName() + "]가 삭제 되었습니다");
                        break;
                }
                break;
            case  "AccountServiceImpl" :
                resultList.add("account");
                switch (methodName) {
                    case "save" :
                        resultList.add("회원가입");
                        resultList.add("'" + getParameterMapValue(parameterMap, "accountId") + "'님 회원 가입되었습니다");
                        break;
                    case "update" :
                        resultList.add("계정정보수정");
                        resultList.add("'" + getParameterMapValue(parameterMap, "accountId") + "'님의 계정정보가 수정되었습니다");
                        break;
                }
                break;
            case  "PageServiceImpl" :
                if (StringUtils.isNotEmpty(getParameterMapValue(parameterMap, "pageName"))) break;
                resultList.add("page");
                switch (methodName) {
                    case "save" :
                        resultList.add("페이지생성");
                        resultList.add("페이지 [" + getParameterMapValue(parameterMap, "pageName") + "] 가 생성 되었습니다");
                        break;
                    case "update" :
                        resultList.add("페이지수정");
                        resultList.add("페이지 [" + getParameterMapValue(parameterMap, "pageName") + "] 가 수정 되었습니다");
                        break;
                    case "delete" :
                        resultList.add("페이지삭제");
                        resultList.add("페이지 [" + getParameterMapValue(parameterMap, "pageName") + "] 가 삭제 되었습니다");
                        break;
                }
                break;
            case  "RoleMappingPageServiceImpl" :
                if (StringUtils.isNotEmpty(getParameterMapValue(parameterMap, "pageName"))
                || StringUtils.isNotEmpty(getParameterMapValue(parameterMap, "roleId"))) break;

                resultList.add("role&page");
                role = roleRepository.findById(getParameterMapValue(parameterMap, "roleId")).get();
                switch (methodName) {
                    case "save" :
                        resultList.add("권한-페이지 연동");
                        resultList.add("권한[" + role.getRoleName() + "]와 페이지 [" + getParameterMapValue(parameterMap, "pageName")
                                + "] 가 연동 되었습니다");
                        break;

                    case "delete" :
                        resultList.add("권한-페이지 연동해제");
                        resultList.add("권한[" + role.getRoleName() + "]와 페이지 [" + getParameterMapValue(parameterMap, "pageName")
                                + "] 가 연동 되었습니다");
                        break;

                }
                break;
            case  "RoleServiceImpl" :
                if (StringUtils.isNotEmpty(getParameterMapValue(parameterMap, "roleId"))) break;
                resultList.add("role");
                role = roleRepository.findById(getParameterMapValue(parameterMap, "roleId")).get();
                switch (methodName) {
                    case "save" :
                        resultList.add("권한생성");
                        resultList.add(" 권한 [" + role.getRoleName() + "]가 생성 되었습니다");
                        break;
                    case "update" :
                        resultList.add("권한수정");
                        resultList.add(" 권한 [" + role.getRoleName() + "]가 수정 되었습니다");
                        break;
                    case "delete" :
                        resultList.add("권한삭");
                        resultList.add(" 권한 [" + role.getRoleName() + "]가 삭제 되었습니다");
                        break;
                }
                break;

        }
        return resultList;
    }

    private String getParameterMapValue(Map<String, Object> parameterMap, String key){
        return String.valueOf(parameterMap.get(key));
    }
}
