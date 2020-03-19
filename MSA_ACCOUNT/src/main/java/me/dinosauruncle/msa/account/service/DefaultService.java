package me.dinosauruncle.msa.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class DefaultService {
    @Autowired
    Map<String, Object> parameterMap;

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

}
