package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.repository.MsaAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class AccountServiceImpl extends AccountService{

    @Autowired
    private MsaAccountRepository accountRepository;
    private Map<String, Object> tempMap = new HashMap<String, Object>();

    @Override
    public boolean isId(String id) {
        Account account = accountRepository.findById(id).get();
        if (account != null) return true;
        else return false;
    }

    @Override
    public boolean isUsePassword(String password) {
        return isPasswordValidation(password);
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
        if (!tempMap.isEmpty()) {
            Set<String> keyList = tempMap.keySet();
            Iterator<String> iterator = keyList.iterator();
            while(iterator.hasNext()){
                String tempKey = iterator.next();
                map.put(tempKey, tempMap.get(tempKey));
            }
            tempMap.clear();
        }
        map.put(key, value);
        return map;
    }

    private boolean isPasswordValidation(String text){
        char[] specialChars = {'!', '@', '#', '$', '%',
                '^', '&', '*', '(', ')', '-', '_', '+', '=',
                '~', '`', ';', ':', '<', '>', '?', '/'};
        char[] chars = text.toCharArray();
        boolean isUpperCase = false;
        boolean isLowerCase = false;
        boolean isDigit = false;
        boolean isSpecialChar =false;
        boolean result = false;

        for (char ch : chars){
            if (Character.isUpperCase(ch)) isUpperCase = true;
        }

        for (char ch : chars){
            if (Character.isLowerCase(ch)) isLowerCase = true;
        }

        for (char ch : chars){
            if (Character.isDigit(ch)) isDigit = true;
        }
        for (char ch : chars){
            for (char ch2 : specialChars){
                if (ch == ch2) isSpecialChar = true;
            }
        }

        if (isUpperCase & isLowerCase & isDigit & isSpecialChar){
            result = true;
        } else {
            String key = "ErrorMessage";
            if (!isUpperCase) {
                tempMap.put(key, passwordValidErrorMessage("대문자"));
            } else if (!isLowerCase) {
                tempMap.put(key, passwordValidErrorMessage("소문자"));
            } else if (!isDigit) {
                tempMap.put(key, passwordValidErrorMessage("숫자"));
            } else if (!isSpecialChar) {
                tempMap.put(key, passwordValidErrorMessage("특수문자"));
            }
        }
        return result;
    }

    private String passwordValidErrorMessage (String type){
        return "비밀번호에" + type + "가 폼함되어 있지 않습니다";
    }
}
