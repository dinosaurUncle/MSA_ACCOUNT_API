package me.dinosauruncle.msa.account.nonaopservice;

import me.dinosauruncle.msa.account.domain.EventMessage;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class EventMessageService {

    public abstract List<EventMessage> aopSave(JoinPoint joinPoint, Map<String, Object> parameterMap);
    public abstract List<EventMessage> save(String serviceType, String methodName, Map<String, Object> parameterMap);
    public abstract List<EventMessage> getEventMessageListFillterByDate(String accountId);
    public abstract List<EventMessage> getEventMessageList();
    public abstract void isCheckChangeUpdate(List<Long> eventMessageIdList);
    public abstract void isCheckChangeUpdate(Long eventMessageId);
    public abstract String getEventMessageContent(String serviceType, String methodName, String accoutId);
    public abstract int nonCheckedCount(String accountId);
    public abstract Map<String, Object> getEventMessagesInfo(String accountId);
}
