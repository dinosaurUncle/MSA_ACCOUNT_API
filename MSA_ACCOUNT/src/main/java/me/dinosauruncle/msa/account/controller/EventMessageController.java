package me.dinosauruncle.msa.account.controller;

import me.dinosauruncle.msa.account.domain.EventMessage;
import me.dinosauruncle.msa.account.nonaopservice.EventMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PutMapping("")
    public Map<String, Object> chackChange(@RequestBody EventMessage eventMessage){
        eventMessageService.isCheckChangeUpdate(eventMessage.getEventMessageId());
        return eventMessageService.getEventMessagesInfo(eventMessage.getAccountId());
    }

}
