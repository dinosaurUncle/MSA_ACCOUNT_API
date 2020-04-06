package me.dinosauruncle.service.portal.controller;

import me.dinosauruncle.service.portal.nonaopservice.AccountMappingRoleExtendService;
import me.dinosauruncle.service.portal.nonaopservice.CommonService;
import me.dinosauruncle.service.portal.service.AccountMappingRoleService;
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

    @Autowired
    AccountMappingRoleExtendService accountMappingRoleExtendService;

    @GetMapping("/{accountId}/{roleId}")
    public Map<String, Object> save(@PathVariable("accountId") String accountId, @PathVariable("roleId") String roleId){
        return accountMappingRoleService.save(accountId, roleId,
                commonService.SerializationKeyAndValue(2, accountId),
                commonService.SerializationKeyAndValue(3, roleId));
    }

    @GetMapping("detail/{accountId}")
    public Map<String, Object> getHighlightInfoAndRoleMappingInfo(@PathVariable("accountId") String accountId){
        return accountMappingRoleExtendService.getHighlightData(accountId);
    }
}
