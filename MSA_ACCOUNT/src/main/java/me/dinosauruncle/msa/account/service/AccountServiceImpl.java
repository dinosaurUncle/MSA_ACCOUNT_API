package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.repository.MsaAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class AccountServiceImpl extends AccountService{
    private static Logger logger = LogManager.getLogger();
    @Autowired
    private MsaAccountRepository accountRepository;

    @Override
    public boolean isId(String id) {
        Account account = null;
        try {
             account = accountRepository.findById(id).get();
        } catch (Exception e){
            logger.error("id에 해당하는 계정이 존재하지 않습니다");
        }
        if (account != null) return true;
        else return false;
    }

    @Override
    public String findNameAndEmailReturnId(String name, String email) {
        return accountRepository.selectId(name, email);
    }

    @Override
    public Account login(Account account) {
        return accountRepository.login(account.getId(), account.getPassword());
    }

    @Override
    public Map<String, Object> restReturnForm(String key, Object value){
        map.clear();
        map.put(key, value);
        return map;
    }

    @Override
    public String newAccountResult(Account account) {
        String result = null;
        try {
            Account returnAccount = accountRepository.save(account);
            result = "S";
        } catch (Exception e) {
            result = "F";
        }
        return result;
    }

    @Override
    public Map<String, Object> addKeyEndValue(String key, Object value) {
        map.put(key, value);
        return map;
    }
}
