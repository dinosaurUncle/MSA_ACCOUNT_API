package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.AccountMappingRole;
import me.dinosauruncle.msa.account.domain.Page;
import me.dinosauruncle.msa.account.domain.RoleMappingPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AccountMappingPageService extends DefaultService {

    @Autowired
    AccountMappingRoleService accountMappingRoleService;

    @Autowired
    RoleMappingPageService roleMappingPageService;

    public Map<String, Object> byAccountIdGetPages(String id){
        List<Page> pages = new ArrayList<Page>();
        List<AccountMappingRole> accountMappingRoles = accountMappingRoleService.getAccountMappingRoleByAccountId(id);
        accountMappingRoles.forEach(accountMappingRole -> {
            List<RoleMappingPage> roleMappingPages =roleMappingPageService.getRoleMappingPageObjectByRoleId(accountMappingRole.getRole().getRoleId());
            roleMappingPages.forEach(roleMappingPage -> {
                pages.add(roleMappingPage.getPage());
            });
        });
        parameterMap.put("pages", pages);
        return parameterMap;
    }
}
