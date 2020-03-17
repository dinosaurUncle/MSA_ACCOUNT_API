package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PageService {
    Map<String, Object> saveAndUpdate(Page page);
    Map<String, Object> getPage(Long pageId);
    Map<String, Object> delete(Long pageId);
    Map<String, Object> getPageList();
}
