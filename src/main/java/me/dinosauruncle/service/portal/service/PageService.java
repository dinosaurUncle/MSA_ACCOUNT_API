package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class PageService extends DefaultService {
    public abstract Map<String, Object> save(Page page, String... args);
    public abstract Map<String, Object> update(Page page, String... args);
    public abstract Map<String, Object> getPage(Long pageId);
    public abstract Map<String, Object> getPageByName(String pageName);
    public abstract Map<String, Object> delete(Long pageId, String... args);
    public abstract Map<String, Object> getPageList();
}
