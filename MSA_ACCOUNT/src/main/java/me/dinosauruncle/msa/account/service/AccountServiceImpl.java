package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.repository.MsaAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public Map<String, Object> restReturnForm(String key, Object value){
        map.clear();
        map.put(key, value);
        return map;
    }

    @Override
    public String newAccountResult(Account account) {
        String result = null;
        try {
            passwordTransEncode(account);
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

    @Override
    public boolean login(Account account) {
        boolean result = false;
        Account selectAccount = accountRepository.findById(account.getId()).get();
        if (passwordEncoder.matches(account.getPassword(), selectAccount.getPassword())){
            result = true;
            logger.info("로그인 성공");
        } else {
            logger.info("로그인 실패");
        }
        return result;
    }

    private void passwordTransEncode(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }

    @Override
    public Account findById(String id) {
        return accountRepository.findById(id).get();
    }
}
