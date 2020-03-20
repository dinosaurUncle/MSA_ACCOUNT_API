package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.AccountMappingRoleService;
import me.dinosauruncle.msa.account.service.AccountService;
import me.dinosauruncle.msa.account.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AccountMappingRoleCRUDTest {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    AccountMappingRoleService accountMappingRoleService;

    @Test
    public void save(){
        AccountMappingRole accountMappingRole = new AccountMappingRole();
        accountMappingRole.setAccount((Account)accountService.getAccount("m05214").get("account"));
        accountMappingRole.setRole((Role)roleService.getRole("initiator").get("role"));
        accountMappingRoleService.save(accountMappingRole);
    }

    @Test
    public void select(){
        List<AccountMappingRole> accountMappingRoles = accountMappingRoleService.getAccountMappingRoleByAccountId("m05214");
        accountMappingRoles.forEach(accountMappingRole -> {
            logger.info(accountMappingRole.getRole().getRoleId());
        });
    }
}
