package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.RoleMappingPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class RoleMappingPageService extends DefaultService {
    public abstract Map<String, Object> save(RoleMappingPage roleMappingPage, String... args);
    public abstract List<RoleMappingPage> getRoleMappingPageObjectByRoleId(String roleId);
    public abstract Map<String, Object> delete(Long id, String... args);
    public abstract RoleMappingPage selectByRoleIdAndPageId(String roleId, Long pageId);
    public abstract boolean validationIsExistCheck(String roleId, Long pageId);
}
