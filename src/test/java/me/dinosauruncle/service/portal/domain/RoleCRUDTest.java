package me.dinosauruncle.service.portal.domain;

import me.dinosauruncle.service.portal.repository.RoleRepository;
import me.dinosauruncle.service.portal.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RoleCRUDTest {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void save(){
        Role role = new Role();
        role.setRoleId("initiator");
        role.setRoleName("가입자");
        roleService.save(role);

    }

    @Test
    public void selectById() {
        Role role = roleRepository.findById("manager").get();
        logger.info(role);
    }

}
