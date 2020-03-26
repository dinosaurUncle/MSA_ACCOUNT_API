package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RoleCRUDTest {

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
