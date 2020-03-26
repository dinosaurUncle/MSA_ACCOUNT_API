package me.dinosauruncle.msa.account.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class DefaultService {
    Map<String, Object> parameterMap;

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
