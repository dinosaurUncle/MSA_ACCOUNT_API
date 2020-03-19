package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class PageService extends DefaultService {
    public abstract Map<String, Object> saveAndUpdate(Page page);
    public abstract Map<String, Object> getPage(Long pageId);
    public abstract Map<String, Object> delete(Long pageId);
    public abstract Map<String, Object> getPageList();
}
