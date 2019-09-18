package me.dinosauruncle.msa.account.controller;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.repository.AccountRepository;
import me.dinosauruncle.msa.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable("id") String id) {
        return accountRepository.findById(id).get();
    }

    @PostMapping("/account")
    public Account newAccount(@RequestBody Account account){
        return accountRepository.save(account);
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
        Map<String, Object>  map = new HashMap<String, Object>();
        map.put("isDelete", "completed");
        map.put("account", getAccountById(id));
        accountRepository.deleteById(id);
        return map;
    }


}
