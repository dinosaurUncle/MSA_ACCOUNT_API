package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public abstract class AccountService extends DefaultService {
    public abstract Map<String, Object> save(Account account);
    public abstract Map<String, Object> update(Account account);
    public abstract Map<String, Object> getAccount(String id);
    public abstract Map<String, Object> getAccounts();
    public abstract Map<String, Object> deleteAccount(String id);
    public abstract Map<String, Object> isId(String id);
    public abstract Map<String, Object> findNameAndEmailReturnId(String name, String email);


    public abstract Map<String, Object> login(Account account);
}
