package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Page;
import me.dinosauruncle.msa.account.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Transactional
@Service
public class PageServiceImpl extends PageService {


    @Autowired
    private PageRepository pageRepository;

    @Override
    public Map<String, Object> saveAndUpdate(Page page) {
        parameterMap.put("page", page);
        pageRepository.save(page);
        return parameterMap;
    }

    @Override
    public Map<String, Object> saveAndUpdateByAccountId(Page page, String accountId) {
        return saveAndUpdateProtected(page);
    }


    private Map<String, Object> saveAndUpdateProtected(Page page) {
        parameterMap.put("page", page);
        pageRepository.save(page);
        return parameterMap;
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
    public Map<String, Object> delete(Long pageId) {
        Page page = pageRepository.findById(pageId).get();
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
