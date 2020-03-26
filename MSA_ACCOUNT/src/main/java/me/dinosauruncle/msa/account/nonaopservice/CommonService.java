package me.dinosauruncle.msa.account.nonaopservice;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

    public String recognitionAccountId(String accountId){
        return "accountId:"+accountId;
    }
}
