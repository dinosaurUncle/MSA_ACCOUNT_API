package me.dinosauruncle.service.portal.controller;

import me.dinosauruncle.service.portal.service.AccountMappingPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("/account_page")
public class AccountMappingPageController {
    @Autowired
    private AccountMappingPageService accountMappingPageService;

    @GetMapping("/page/{id}")
    public Map<String, Object> getPages(@PathVariable("id") String accountId) {return accountMappingPageService.byAccountIdGetPages(accountId);}
}
