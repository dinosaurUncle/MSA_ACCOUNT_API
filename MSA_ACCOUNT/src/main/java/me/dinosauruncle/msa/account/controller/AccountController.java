package me.dinosauruncle.msa.account.controller;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.repository.MsaAccountRepository;
import me.dinosauruncle.msa.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RestController(value = "/msa")
public class AccountController {

    @Autowired
    private MsaAccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    public AccountController(MsaAccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @PostMapping("/account")
    public Map<String, Object> save(@RequestBody Account account){
        return accountService.save(account);
    }

    @PutMapping("/account")
    public Map<String, Object> update(@RequestBody Account account){
        return accountService.update(account);
    }
    @GetMapping("/account/{id}")
    public Map<String, Object> getAccountById(@PathVariable("id") String id) {
        return accountService.findById(id);
    }
    @GetMapping("/account")
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/account/password/{email}")
    public String createTokenAndSendEmail(@PathVariable("email") String email) {
        return null;
    }


    @DeleteMapping("/account/{id}")
    public Map<String, Object> deleteAccount(@PathVariable("id") String id){
        return accountService.deleteAccount(id);
    }

    @GetMapping("account/isId/{id}")
    public Map<String, Object> isId(@PathVariable("id") String id) {
        return accountService.isId(id);
    }

    @GetMapping("account/selectId/{name}/{email}")
    public Map<String, Object> findNameAndEmailReturnId(
            @PathVariable("name") String name, @PathVariable("email") String email){
        accountService.setMap(map);
        return accountService.restReturnForm("id", accountService.findNameAndEmailReturnId(name, email));
    }

    @PostMapping("account/login")
    public Map<String, Object> login(@RequestBody Account account){
        final Map<String, Object> loginResult = accountService.login(account);
        return Boolean.valueOf(String.valueOf(map.get("login"))) ?
                accountService.addKeyEndValue("account", accountService.findById(account.getId()))
                : loginResult;
    }




}
