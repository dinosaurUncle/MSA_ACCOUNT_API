package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.AccountService;
import me.dinosauruncle.msa.account.service.PageService;
import me.dinosauruncle.msa.account.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class RoleCRUDTest {

    @Autowired
    PageService pageService;

    @Autowired
    RoleService roleService;

    @Test
    public void save(){

        Role role = new Role();
        role.setRoleId("initiator");
        role.setRoleName("가입자");
        roleService.save(role);

    }

}
