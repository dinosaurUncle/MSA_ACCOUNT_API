package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.Account;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class AccountService extends DefaultService {
    public abstract Map<String, Object> save(Account account);
    public abstract Map<String, Object> update(Account account, String... strings);
    public abstract Map<String, Object> getAccount(String id);
    public abstract Map<String, Object> getAccounts();
    public abstract Map<String, Object> deleteAccountId(String id, String... strings);
    public abstract Map<String, Object> deleteAccount(String targetAccountId, Account account);
    public abstract Map<String, Object> isId(String id);
    public abstract Map<String, Object> findNameAndEmailReturnId(String name, String email);


    public abstract Map<String, Object> login(Account account);
}
