package me.dinosauruncle.msa.account.controller;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.nonaopservice.CommonService;
import me.dinosauruncle.msa.account.service.AccountMappingRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/account_role")
public class AccountMappingRoleController {

    @Autowired
    AccountMappingRoleService accountMappingRoleService;

    @Autowired
    CommonService commonService;

    @GetMapping("/{accountId}/{roleId}")
    public Map<String, Object> save(@PathVariable("accountId") String accountId, @PathVariable("roleId") String roleId){
        return accountMappingRoleService.save(accountId, roleId,
                commonService.SerializationKeyAndValue(2, accountId),
                commonService.SerializationKeyAndValue(3, roleId));
    }
}
