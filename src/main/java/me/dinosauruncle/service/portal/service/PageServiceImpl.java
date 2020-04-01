package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.Page;
import me.dinosauruncle.service.portal.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Transactional
@Service
public class PageServiceImpl extends PageService {


    @Autowired
    private PageRepository pageRepository;


    public Map<String, Object> saveAndUpdate(Page page, String... args) {
        parameterMap.put("page", page);
        pageRepository.save(page);
        return parameterMap;
    }

    @Override
    public Map<String, Object> save(Page page, String... args) {
        return saveAndUpdate(page, args);
    }

    @Override
    public Map<String, Object> update(Page page, String... args) {
        return saveAndUpdate(page, args);
    }

    @Override
    public Map<String, Object> getPage(Long pageId) {
        parameterMap.put("page",pageRepository.findById(pageId).get());
        return parameterMap;
    }

    @Override
    public Map<String, Object> getPageByName(String pageName) {
        parameterMap.put("page",pageRepository.selectByPageName(pageName));
        return parameterMap;
    }

    @Override
    public Map<String, Object> delete(Long pageId, String... args) {
        Page page = pageRepository.findById(pageId).get();
        parameterMap.put("pageName", page.getPageName());
        parameterMap.put("deletePage", page);
        pageRepository.delete(page);
        return parameterMap;
    }

    @Override
    public Map<String, Object> getPageList() {
        parameterMap.put("pages", pageRepository.findAll());
        return parameterMap;
    }

    public void resultMapClean(){
        parameterMap.clear();
    }
}
