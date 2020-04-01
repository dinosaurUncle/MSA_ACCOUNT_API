package me.dinosauruncle.service.portal.nonaopservice;

import me.dinosauruncle.msa.account.domain.*;
import me.dinosauruncle.service.portal.repository.AccountMappingRoleRepository;
import me.dinosauruncle.service.portal.repository.EventMessageRepository;
import me.dinosauruncle.service.portal.repository.PageRepository;
import me.dinosauruncle.service.portal.repository.RoleRepository;
import me.dinosauruncle.service.portal.domain.Account;
import me.dinosauruncle.service.portal.domain.AccountMappingRole;
import me.dinosauruncle.service.portal.domain.EventMessage;
import me.dinosauruncle.service.portal.domain.Role;
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
            case "account" :
                if (resultFieldList.size() == 3){
                    if (!resultFieldList.get(1).equals("delete"))
                        returnEventMessages.add(eachSave(resultFieldList, getParameterMapValue(parameterMap, "accountId")));

                    if(StringUtils.isNotEmpty(getParameterMapValue(parameterMap, "targetAccountId")))
                        returnEventMessages.add(eachSave(resultFieldList, getParameterMapValue(parameterMap, "targetAccountId")));

                }
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
        Map<String, String> tempDateMap = new HashMap<String, String>();
        List<EventMessage> eventMessages = new ArrayList<EventMessage>();
        eventMessageRepository.selectByAccountId(accountId).forEach(eventMessage -> {
            LocalDateTime date = eventMessage.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (tempDateMap.size() == 0){
                String tempDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                tempDateMap.put(tempDate, tempDate);
                eventMessages.add(eventMessage);
            } else {

                if (tempDateMap.containsKey(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
                    eventMessage.setDate(null);
                    eventMessages.add(eventMessage);
                }
                else{
                    String tempDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    tempDateMap.put(tempDate, tempDate);
                    eventMessages.add(eventMessage);
                }

            }
        });

        return eventMessages;
    }

    @Override
    public List<EventMessage> getEventMessageList() {
        return eventMessageRepository.findAll();
    }

    @Override
    public void isCheckChangeUpdate(List<Long> eventMessageIdList) {
        eventMessageIdList.forEach(eventMessageId -> {
            isCheckChangeUpdate(eventMessageId);
        });
    }

    @Override
    public void isCheckChangeUpdate(Long eventMessageId) {
        EventMessage eventMessage = eventMessageRepository.findById(Long.valueOf(eventMessageId)).get();
        eventMessage.setCheck(true);
        eventMessageRepository.save(eventMessage);
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
                if (StringUtils.isEmpty(getParameterMapValue(parameterMap, "roleId"))) break;
                resultList.add("account&role");
                role = roleRepository.findById(getParameterMapValue(parameterMap, "roleId")).get();
                switch (methodName) {
                    case "save" :
                        resultList.add("권한부여");
                        resultList.add("'" + getParameterMapValue(parameterMap, "targetAccountId") + "'님에게 ["
                        + role.getRoleName() + "] 권한이 부여 되었습니다");
                        break;
                    case  "delete" :
                        resultList.add("권한삭제");
                        resultList.add("'" + getParameterMapValue(parameterMap, "targetAccountId") + "'님의 ["
                                + role.getRoleName() + "] 권한이 삭제 되었습니다");
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
                    case "deleteAccountId" :
                        resultList.add("계정정보삭제");
                        resultList.add("'" + getParameterMapValue(parameterMap, "accountId") + "'님의 계정정보가 삭제되었습니다");
                        break;
                }
                break;
            case  "PageServiceImpl" :
                if (StringUtils.isEmpty(getParameterMapValue(parameterMap, "pageName"))) break;
                resultList.add("page");
                switch (methodName) {
                    case "save" :
                        resultList.add("페이지생성");
                        resultList.add("[" + getParameterMapValue(parameterMap, "pageName") + "] 페이지가 생성 되었습니다");
                        break;
                    case "update" :
                        resultList.add("페이지수정");
                        resultList.add("[" + getParameterMapValue(parameterMap, "pageName") + "] 페이지가 수정 되었습니다");
                        break;
                    case "delete" :
                        resultList.add("페이지삭제");
                        resultList.add("[" + getParameterMapValue(parameterMap, "pageName") + "] 페이지가 삭제 되었습니다");
                        break;
                }
                break;
            case  "RoleMappingPageServiceImpl" :
                if (StringUtils.isEmpty(getParameterMapValue(parameterMap, "pageName"))
                || StringUtils.isEmpty(getParameterMapValue(parameterMap, "roleId"))) break;

                resultList.add("role&page");
                role = roleRepository.findById(getParameterMapValue(parameterMap, "roleId")).get();
                switch (methodName) {
                    case "save" :
                        resultList.add("권한-페이지 연동");
                        resultList.add("[" + role.getRoleName() + "] 권한과 [" + getParameterMapValue(parameterMap, "pageName")
                                + "] 페이지 가 연동 되었습니다");
                        break;

                    case "delete" :
                        resultList.add("권한-페이지 연동해제");
                        resultList.add("[" + role.getRoleName() + "] 권한과 [" + getParameterMapValue(parameterMap, "pageName")
                                + "] 페이지 가 연동 되었습니다");
                        break;

                }
                break;
            case  "RoleServiceImpl" :
                if (StringUtils.isEmpty(getParameterMapValue(parameterMap, "roleId"))) break;
                resultList.add("role");
                switch (methodName) {
                    case "save" :
                        resultList.add("권한생성");
                        resultList.add(" [" + getParameterMapValue(parameterMap, "roleId") + "] 권한이 생성 되었습니다");
                        break;
                    case "update" :
                        resultList.add("권한수정");
                        resultList.add(" [" + getParameterMapValue(parameterMap, "roleId") + "] 권한이 수정 되었습니다");
                        break;
                    case "delete" :
                        resultList.add("권한삭제");
                        resultList.add(" [" + getParameterMapValue(parameterMap, "roleId") + "] 권한이 삭제 되었습니다");
                        break;
                }
                break;

        }
        return resultList;
    }

    private String getParameterMapValue(Map<String, Object> parameterMap, String key){
        return String.valueOf(parameterMap.get(key)) == "null"? null : String.valueOf(parameterMap.get(key));
    }
}
