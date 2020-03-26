package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.PageService;
import me.dinosauruncle.msa.account.service.RoleMappingPageService;
import me.dinosauruncle.msa.account.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoleMappingPageCRUDTest {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    RoleService roleService;

    @Autowired
    PageService pageService;

    @Autowired
    RoleMappingPageService roleMappingPageService;

    @Test
    public void save(){
        Role role = (Role) roleService.getRole("initiator").get("role");
        Page page = (Page) pageService.getPage(2L).get("page");
        RoleMappingPage roleMappingPage = new RoleMappingPage();
        roleMappingPage.setRole(role);
        roleMappingPage.setPage(page);
        roleMappingPageService.save(roleMappingPage);
    }

    @Test
    public void select(){
        List<RoleMappingPage> roleMappingPageList = roleMappingPageService.getRoleMappingPageObjectByRoleId("initiator");
        roleMappingPageList.forEach(roleMappingPage -> {
            logger.info(roleMappingPage.getPage());
        });
    }

    @Test
    public void validationIsExist(){
        logger.info(roleMappingPageService.validationIsExistCheck("initiator", 1L));
    }

    @Test
    public void delete(){
        roleMappingPageService.delete(2L);
    }
}
