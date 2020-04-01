package me.dinosauruncle.service.portal.domain;

import me.dinosauruncle.service.portal.controller.AccountController;
import me.dinosauruncle.service.portal.nonaopservice.CommonService;
import me.dinosauruncle.service.portal.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountCRUDTest {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    AccountService accountService;

    @Autowired
    CommonService commonService;

    @Autowired
    AccountController accountController;


    @Test
    public void save(){
        /*
        Account account = new Account();
        account.setAccountId("test123");
        account.setPassword("test123");
        account.setAccountName("이지은");
        account.setGender(Gender.FEMALE);
        account.setEmail("I-U@naver.com");
        account.setPhone("010-1111-2222");
        accountService.save(account);
        */

        Account account = new Account();
        account.setAccountId("test11122");
        account.setPassword("pass");
        account.setAccountName("테스터");
        account.setGender(Gender.MALE);
        account.setEmail("tester@naver.com");
        account.setPhone("010-1111-2222");
        accountController.save(account);
        //accountService.save(account);
    }

    @Test
    public void update(){
        Account account = new Account();
        account.setAccountId("m05214");;
        account.setPhone("010-3333-4444");
        accountService.update(account);
    }

    @Test
    public void getAccount(){
        accountService.getAccount("test123");
    }

    @Test
    public void getList(){
        accountService.getAccounts();
    }

    @Test
    public void delete(){
        Account account = new Account();
        account.setAccountId("test123");;
        accountService.deleteAccount(commonService.SerializationKeyAndValue(2, "m05214"), account);
    }

    @Test
    public void isId(){
        accountService.isId("test123");
    }

    @Test
    public void login(){
        Account account = new Account();
        account.setAccountId("m05214");
        account.setPassword("pass");
        accountService.login(account);
    }


}
