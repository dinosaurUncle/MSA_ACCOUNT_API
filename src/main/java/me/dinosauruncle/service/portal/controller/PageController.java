package me.dinosauruncle.service.portal.controller;

import me.dinosauruncle.service.portal.domain.Page;
import me.dinosauruncle.service.portal.nonaopservice.CommonService;
import me.dinosauruncle.service.portal.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private CommonService commonService;

    @GetMapping("/admin")
    public Map<String, Object> getPages(){
        return pageService.getPageList();
    }

    @PostMapping("/admin/{accountId}")
    public Map<String, Object> saveByAdmin(@PathVariable("accountId")String accountId,
                                           @RequestBody Page page){
        pageService.save(page, commonService.SerializationKeyAndValue(1, accountId),
                commonService.SerializationKeyAndValue(4, String.valueOf(page.getPageName())));
        return pageService.getPageList();
    }

    @PutMapping("/admin/{accountId}")
    public Map<String, Object> updateByAdmin(@PathVariable("accountId")String accountId,
                                                   @RequestBody Page page){
        pageService.update(page,commonService.SerializationKeyAndValue(1, accountId),
                commonService.SerializationKeyAndValue(4, String.valueOf(page.getPageName())));
        return pageService.getPageList();
    }
    @DeleteMapping("/admin/{pageId}/{accountId}")
    public Map<String, Object> deleteByAdmin(@PathVariable("pageId")String pageId,
                                             @PathVariable("accountId")String accountId) {
        pageService.delete(Long.valueOf(pageId), commonService.SerializationKeyAndValue(1, accountId),
                commonService.SerializationKeyAndValue(4, pageId));
        return pageService.getPageList();
    }

}
