package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.EventMessage;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class EventMessageService {

    public abstract EventMessage save(JoinPoint joinPoint, Map<String, Object> parameterMap);
    public abstract List<EventMessage> getEventMessageList(String accountId);
    public abstract void update(List<String> eventMessageList);
    public abstract String getEventMessageContent(String serviceType, String methodName, Map<String, Object> parameterMap);
}
