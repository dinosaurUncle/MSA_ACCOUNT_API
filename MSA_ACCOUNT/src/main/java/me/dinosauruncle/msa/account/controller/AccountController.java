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

    private Map<String, Object> map;
    @Autowired
    public AccountController(MsaAccountRepository accountRepository){
        this.accountRepository = accountRepository;
        map = new HashMap<String, Object>();
    }

    @GetMapping("/account")
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable("id") String id) {
        return accountService.findById(id);
    }

    @PostMapping("/account")
    public Map<String, Object> newAccount(@RequestBody Account account)
    {
        accountService.setMap(map);
        accountService.restReturnForm("account", account);
        return accountService.addKeyEndValue("state", accountService.newAccountResult(account));
    }

    @PutMapping("/account/{id}")
    public Account updateAccount(@PathVariable("id") String id,
                                 @RequestBody Account account){
        return accountRepository.findById(id)
                .map(inputAccount -> {
                    if (account.getName() != null)
                        inputAccount.setName(account.getName());
                    if (account.getPassword() != null)
                        inputAccount.setPassword((account.getPassword()));
                    if (account.getGender() != null)
                        inputAccount.setGender(account.getGender());
                    if (account.getEmail() != null)
                        inputAccount.setEmail(account.getEmail());
                    if (account.getPhone() != null)
                        inputAccount.setPhone(account.getPhone());
                    return accountRepository.save(inputAccount);
                }).orElseGet(() -> {
                   account.setId(id);
                   return accountRepository.save(account);
                });
    }

    @DeleteMapping("/account/{id}")
    public Map<String, Object> deleteAccount(@PathVariable("id") String id){
        accountService.setMap(map);
        map.clear();
        map.put("isDelete", "completed");
        map.put("account", getAccountById(id));
        accountRepository.deleteById(id);
        return map;
    }

    @GetMapping("account/isId/{id}")
    public Map<String, Object> isId(@PathVariable("id") String id) {
        accountService.setMap(map);
        return accountService.restReturnForm("isId", accountService.isId(id));
    }

    @GetMapping("account/name/email/{name}/{email}")
    public Map<String, Object> findNameAndEmailReturnId(
            @PathVariable("name") String name, @PathVariable("email") String email){
        accountService.setMap(map);
        return accountService.restReturnForm("id", accountService.findNameAndEmailReturnId(name, email));
    }

    @PostMapping("account/login")
    public Map<String, Object> login(@RequestBody Account account){
        final boolean loginResult = accountService.login(account);
        accountService.setMap(map);
        accountService.restReturnForm("login", loginResult);
        return loginResult ?
                accountService.addKeyEndValue("account", accountService.findById(account.getId()))
                : accountService.addKeyEndValue("account", null);
    }




}
