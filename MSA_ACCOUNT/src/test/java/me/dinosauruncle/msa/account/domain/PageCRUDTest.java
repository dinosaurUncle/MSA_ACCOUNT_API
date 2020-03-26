package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.PageService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageCRUDTest {

    @Autowired
    PageService pageService;


    @Test
    public void save(){
        Page page = new Page();
        page.setPageName("Test");
        page.setPageUrl("/test11111");
        page.setDescription("testtesttest");
        pageService.saveAndUpdateByAccountId(page, "accountId:m05214");
    }

    @Test
    public void update(){
        Page page = new Page();
        page.setPageId(2L);
        page.setPageName("Home");
        page.setPageUrl("/");
        page.setDescription("모든 계정에 보이는 페이지 입니다");
        pageService.saveAndUpdate(page);
    }


    @Test
    public void getPage(){
        pageService.getPage(2L);
    }

    @Test
    public void getPages(){
        pageService.getPageList();
    }

    @Test
    public void delete(){
        pageService.delete(5L);
    }
}
