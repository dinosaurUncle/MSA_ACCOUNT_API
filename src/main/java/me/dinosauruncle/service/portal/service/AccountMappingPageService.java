package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.AccountMappingRole;
import me.dinosauruncle.service.portal.domain.Page;
import me.dinosauruncle.service.portal.domain.RoleMappingPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Page> distinctAfterPages =pages.stream().distinct().collect(Collectors.toList());
        parameterMap.put("pages", distinctAfterPages);
        return parameterMap;
    }
}
