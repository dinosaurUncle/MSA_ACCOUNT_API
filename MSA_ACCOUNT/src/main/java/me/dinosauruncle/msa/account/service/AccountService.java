package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public abstract class AccountService {

    protected Map<String, Object> map;

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public abstract boolean isId(String id);
    public abstract String findNameAndEmailReturnId(String name, String email);
    public abstract Map<String, Object> addKeyEndValue(String key, Object value);
    public abstract Map<String, Object> restReturnForm(String key, Object value);
    public abstract Account findById(String id);
    public abstract String newAccountResult (Account account);
    public abstract Map<String, Object> login(Account account);
}
