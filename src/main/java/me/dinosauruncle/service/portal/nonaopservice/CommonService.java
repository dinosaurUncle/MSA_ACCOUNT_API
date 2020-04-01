package me.dinosauruncle.service.portal.nonaopservice;

import org.springframework.stereotype.Service;

@Service
public class CommonService {
    private final String ACCOUNT_ID = "accountId";
    private final String TARGET_ACCOUNT_ID = "targetAccountId";
    private final String ROLE_ID = "roleId";
    private final String PAGE_NAME = "pageName";

    public String SerializationKeyAndValue(int type, String value){
        String key = null;
        switch (type){
            case 1 :
                key = ACCOUNT_ID;
                break;
            case 2 :
                key = TARGET_ACCOUNT_ID;
                break;
            case 3 :
                key = ROLE_ID;
                break;
            case 4 :
                key = PAGE_NAME;
                break;
        }
        return key + ":" + value;
    }
}
