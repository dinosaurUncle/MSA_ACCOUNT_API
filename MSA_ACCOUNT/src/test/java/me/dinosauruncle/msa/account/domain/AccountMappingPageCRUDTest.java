package me.dinosauruncle.msa.account.domain;

import me.dinosauruncle.msa.account.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AccountMappingPageCRUDTest {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private AccountMappingPageService accountMappingPageService;

    @Test
    public void byAccountIdGetPageInfo(){
        logger.info("m05214 pages: " + accountMappingPageService.byAccountIdGetPages("m05214"));
        logger.info("IU_Love pages: " + accountMappingPageService.byAccountIdGetPages("IU_Love"));

    }
}
