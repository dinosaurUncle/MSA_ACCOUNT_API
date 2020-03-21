package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicDataSetting {

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    PageService pageService;

    @Autowired
    AccountMappingRoleService accountMappingRoleService;

    @Autowired
    RoleMappingPageService roleMappingPageService;

    @Test
    public void dataSetting1(){
        Account account = new Account();
        account.setAccountId("m05214");
        account.setPassword("pass");
        account.setAccountName("박종훈");
        account.setGender(Gender.MALE);
        account.setEmail("m05214@naver.com");
        account.setPhone("010-1111-2222");
        accountService.save(account);

        Account account2 = new Account();
        account2.setAccountId("IU_Love");
        account2.setPassword("pass");
        account2.setAccountName("아이유");
        account2.setGender(Gender.FEMALE);
        account2.setEmail("IU@naver.com");
        account2.setPhone("010-3333-4444");
        accountService.save(account2);

        Role role = new Role();
        role.setRoleId("initiator");
        role.setRoleName("가입자");
        roleService.save(role);

        Role admin = new Role();
        admin.setRoleId("admin");
        admin.setRoleName("관리자");
        roleService.save(admin);

        Page page = new Page();
        page.setPageName("Home");
        page.setPageUrl("/");
        page.setDescription("initiation page");
        pageService.saveAndUpdate(page);

        Page newsPage = new Page();
        newsPage.setPageName("News");
        newsPage.setPageUrl("/news");
        newsPage.setDescription("news page");
        pageService.saveAndUpdate(newsPage);

        Page adminPage = new Page();
        adminPage.setPageName("Admin");
        adminPage.setPageUrl("/admin");
        adminPage.setDescription("admin page");
        pageService.saveAndUpdate(adminPage);

        AccountMappingRole accountMappingRole = new AccountMappingRole();
        accountMappingRole.setAccount(account);
        accountMappingRole.setRole(role);
        accountMappingRoleService.save(accountMappingRole);

        AccountMappingRole accountMappingRole2 = new AccountMappingRole();
        accountMappingRole2.setAccount(account2);
        accountMappingRole2.setRole(role);
        accountMappingRoleService.save(accountMappingRole2);

        AccountMappingRole accountMappingRole3 = new AccountMappingRole();
        accountMappingRole3.setAccount(account);
        accountMappingRole3.setRole(admin);
        accountMappingRoleService.save(accountMappingRole3);

        RoleMappingPage roleMappingPage = new RoleMappingPage();
        roleMappingPage.setRole(role);
        roleMappingPage.setPage(page);
        roleMappingPageService.save(roleMappingPage);

        RoleMappingPage roleMappingPage2 = new RoleMappingPage();
        roleMappingPage2.setRole(role);
        roleMappingPage2.setPage(newsPage);
        roleMappingPageService.save(roleMappingPage2);

        RoleMappingPage roleMappingPage3 = new RoleMappingPage();
        roleMappingPage3.setRole(admin);
        roleMappingPage3.setPage(page);
        roleMappingPageService.save(roleMappingPage3);

        RoleMappingPage roleMappingPage4 = new RoleMappingPage();
        roleMappingPage4.setRole(admin);
        roleMappingPage4.setPage(adminPage);
        roleMappingPageService.save(roleMappingPage4);
    }

    @Test
    public void dataSetting2(){

    }

}
