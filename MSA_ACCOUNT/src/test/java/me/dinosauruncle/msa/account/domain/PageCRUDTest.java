package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.AccountService;
import me.dinosauruncle.msa.account.service.PageService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageCRUDTest {

    @Autowired
    PageService pageService;

    @Autowired
    AccountService accountService;


    @Test
    public void save(){
        Page page = new Page();
        page.setPageName("Home");
        page.setPageUrl("/");
        page.setDescription("기본 페이지");
        pageService.saveAndUpdate(page);
    }
}
