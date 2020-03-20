package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.AccountMappingRoleService;
import me.dinosauruncle.msa.account.service.AccountService;
import me.dinosauruncle.msa.account.service.RoleMappingPageService;
import me.dinosauruncle.msa.account.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AccountMappingPageCRUDTest {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    AccountMappingRoleService accountMappingRoleService;

    @Autowired
    RoleMappingPageService roleMappingPageService;

    @Test
    public void byAccountIdGetPageInfo(){
        List<AccountMappingRole> accountMappingRoles = accountMappingRoleService.getAccountMappingRoleByAccountId("m05214");
        accountMappingRoles.forEach(accountMappingRole -> {
            List<RoleMappingPage> roleMappingPages = roleMappingPageService.getRoleMappingPageObjectByRoleId(accountMappingRole.getRole().getRoleId());
            roleMappingPages.forEach(roleMappingPage -> {
                logger.info(roleMappingPage.getPage().getPageId());
                logger.info(roleMappingPage.getPage().getPageName());
            });
        });
    }
}
