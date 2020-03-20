package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.AccountService;
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


    @Test
    public void save(){
        Account account = new Account();
        account.setId("test123");
        account.setPassword("test123");
        account.setName("이지은");
        account.setGender(Gender.FEMALE);
        account.setEmail("I-U@naver.com");
        account.setPhone("010-1111-2222");
        accountService.save(account);
    }

    @Test
    public void update(){
        Account account = new Account();
        account.setId("m05214");;
        account.setPhone("010-3333-4444");
        accountService.update(account);
    }

    @Test
    public void getAccount(){
        accountService.findById("test123");
    }

    @Test
    public void getList(){
        accountService.getAccounts();
    }

    @Test
    public void delete(){
        Account account = new Account();
        account.setId("test123");;
        accountService.deleteAccount("test123");
    }

    @Test
    public void isId(){
        accountService.isId("test123");
    }

    @Test
    public void login(){
        Account account = new Account();
        account.setId("m05214");
        account.setPassword("pass");
        accountService.login(account);
    }


}
