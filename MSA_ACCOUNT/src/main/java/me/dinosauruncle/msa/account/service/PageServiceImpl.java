package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Page;
import me.dinosauruncle.msa.account.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class PageServiceImpl implements  PageService {

    private Map<String, Object> resultMap;
    public PageServiceImpl(){
        resultMap = new HashMap<String, Object>();
    }

    @Autowired
    private PageRepository pageRepository;

    @Override
    public Map<String, Object> saveAndUpdate(Page page) {
        resultMap.put("page", page);
        pageRepository.save(page);
        return resultMap;
    }

    @Override
    public Map<String, Object> getPage(Long pageId) {
        return null;
    }

    @Override
    public Map<String, Object> delete(Long pageId) {
        return null;
    }

    @Override
    public Map<String, Object> getPageList() {
        return null;
    }

    public void resultMapClean(){
        resultMap.clear();
    }
}
