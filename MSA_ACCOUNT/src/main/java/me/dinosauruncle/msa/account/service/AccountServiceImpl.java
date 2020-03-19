package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.repository.MsaAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class AccountServiceImpl extends AccountService{
    private static Logger logger = LogManager.getLogger();
    @Autowired
    private MsaAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> save(Account account) {
        Account returnAccount = null;
        try {
            passwordTransEncode(account);
            returnAccount = accountRepository.save(account);
        } catch (Exception e) {
            logger.error("account saving occur error");
        }
        return saveAndUpdate(returnAccount);
    }

    @Override
    public Map<String, Object> update(Account account) {
        Account getAccount = null;
        try {
            getAccount = (Account) findById(account.getId()).get("account");
            getAccount.update(account);

        } catch (Exception e) {
            logger.error("account updating occur error");
        }
        return saveAndUpdate(getAccount);
    }

    public Map<String, Object> saveAndUpdate(Account account) {
        try {
            Account returnAccount = accountRepository.save(account);
            parameterMap.put("account", returnAccount);
            parameterMap.put("state", "S");
        } catch (Exception e) {
            parameterMap.put("state", "F");
        }
        return parameterMap;
    }

    @Override
    public Map<String, Object> findById(String id) {
        try {
            parameterMap.put("account", accountRepository.findById(id).get());
        } catch (NoSuchElementException e){
            String message = "존재하지 않는 아이디 입니다";
            logger.error(message);
            parameterMap.put("message", message);
        }
        return parameterMap;
    }

    @Override
    public Map<String, Object> getAccounts() {
        parameterMap.put("accounts", accountRepository.findAll());
        return parameterMap;
    }

    @Override
    public Map<String, Object> deleteAccount(String id) {
        Account account = accountRepository.findById(id).get();
        parameterMap.put("account", account);
        accountRepository.delete(account);
        return parameterMap;
    }

    @Override
    public Map<String, Object> isId(String id) {
        boolean result = false;
        try {
             result = accountRepository.existsById(id);
        } catch (Exception e){
            logger.error("id에 해당하는 계정이 존재하지 않습니다");
        }
        parameterMap.put("result", String.valueOf(result));
        return parameterMap;
    }

    @Override
    public Map<String, Object> findNameAndEmailReturnId(String name, String email) {
        parameterMap.put("id", accountRepository.selectId(name, email));
        return parameterMap;
    }


    @Override
    public Map<String, Object> login(Account account) {
        Account selectAccount = null;
        try {
            selectAccount = accountRepository.findById(account.getId()).get();
            if (passwordEncoder.matches(account.getPassword(), selectAccount.getPassword())){
                parameterMap.put("login", true);
                selectAccount.setPassword(null);
                parameterMap.put("account", selectAccount);
                logger.info("로그인 성공");
            } else {
                String message = "ID와 비밀번호가 불일치 합니다";
                parameterMap.put("login", false);
                parameterMap.put("message", message);
                logger.info("로그인 실패");
            }
        } catch (NoSuchElementException e){
            String message = "존재하지 않는 아이디 입니다";
            parameterMap.put("login", false);
            parameterMap.put("message", message);
            logger.error(message);

        } catch (NullPointerException e){
            String message = "존재하지 않는 아이디 입니다";
            parameterMap.put("login", false);
            parameterMap.put("message", message);
            logger.error(message);
        }
        return parameterMap;
    }

    private void passwordTransEncode(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }
}
