package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.nonaopservice.EventMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EventMessageCRUTest {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    EventMessageService eventMessageService;

    @Test
    public void save(){

    }

    @Test
    public void isCheckChangeUpdate(){
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        eventMessageService.isCheckChangeUpdate(list);
    }

    @Test
    public void getEventMessagesByAccountId(){
        logger.info(eventMessageService.getEventMessageListFillterByDate("m05214"));
    }

    @Test
    public void nonCheckedCount(){
        logger.info(eventMessageService.nonCheckedCount("m05214"));
    }

    @Test
    public void getEventMessagesInfo(){
        logger.info(eventMessageService.getEventMessagesInfo("m05214"));
    }
}
