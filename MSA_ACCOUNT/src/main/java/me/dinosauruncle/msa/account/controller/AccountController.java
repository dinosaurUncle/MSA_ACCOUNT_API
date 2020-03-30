package me.dinosauruncle.msa.account.controller;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.nonaopservice.CommonService;
import me.dinosauruncle.msa.account.service.AccountMappingPageService;
import me.dinosauruncle.msa.account.service.AccountMappingRoleService;
import me.dinosauruncle.msa.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AccountMappingRoleService accountMappingRoleService;



    @PostMapping("")
    public Map<String, Object> save(@RequestBody Account account){
        Map<String, Object> result = accountService.save(account);
        accountMappingRoleService.save(account.getAccountId(), "initiator");
        return result;
    }

    @PutMapping("{accountId}")
    public Map<String, Object> update(@PathVariable("accountId") String accountId, @RequestBody Account account){
        return accountService.update(account, commonService.SerializationKeyAndValue(2, accountId));
    }
    @PutMapping("/admin/{accountId}")
    public Map<String, Object> updateAfterSelectAccountList(@PathVariable("accountId") String accountId, @RequestBody Account account){
        accountService.update(account, commonService.SerializationKeyAndValue(2, accountId));
        return accountService.getAccounts();
    }
    @GetMapping("/{id}")
    public Map<String, Object> getAccountById(@PathVariable("id") String id) {
        return accountService.getAccount(id);
    }
    @GetMapping("")
    public Map<String, Object> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/password/{email}")
    public String createTokenAndSendEmail(@PathVariable("email") String email) {
        return null;
    }


    @DeleteMapping("/{id}")
    public Map<String, Object> deleteAccountId(@PathVariable("id") String id){
        return accountService.deleteAccountId(id);
    }

    @DeleteMapping("/admin/{accountId}/{targetAccountId}")
    public Map<String, Object> deleteAccountAfterSelectAccountList(@PathVariable("accountId") String accountId,
                                                                   @PathVariable("targetAccountId") String targetAccountId){
        accountService.deleteAccountId(accountId, commonService.SerializationKeyAndValue(2, targetAccountId));
        return accountService.getAccounts();
    }

    @GetMapping("/isId/{id}")
    public Map<String, Object> isId(@PathVariable("id") String id) {
        return accountService.isId(id);
    }

    @GetMapping("/selectId/{name}/{email}")
    public Map<String, Object> findNameAndEmailReturnId(
            @PathVariable("name") String name, @PathVariable("email") String email){
        return accountService.findNameAndEmailReturnId(name, email);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Account account){
        return accountService.login(account);
    }




}
