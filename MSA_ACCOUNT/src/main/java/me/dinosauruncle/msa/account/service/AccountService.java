package me.dinosauruncle.msa.account.service;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    public boolean isId(String id);
    public boolean isUsePassword(String password);
    public String findNameAndEmailReturnId(String name, String email);
    public boolean login(String id, String password);
}
