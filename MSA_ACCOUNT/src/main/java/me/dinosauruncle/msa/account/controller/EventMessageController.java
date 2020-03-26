package me.dinosauruncle.msa.account.controller;

import me.dinosauruncle.msa.account.nonaopservice.EventMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/eventMessage")
public class EventMessageController {

    @Autowired
    EventMessageService eventMessageService;

    @GetMapping("/{accountId}")
    public Map<String, Object> getEventMessagesInfo(@PathVariable("accountId") String accountId){
        return eventMessageService.getEventMessagesInfo(accountId);
    }

}
