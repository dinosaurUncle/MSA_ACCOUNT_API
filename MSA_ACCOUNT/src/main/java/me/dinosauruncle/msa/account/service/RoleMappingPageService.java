package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.RoleMappingPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class RoleMappingPageService extends DefaultService {
    public abstract Map<String, Object> save(RoleMappingPage roleMappingPage);
    public abstract List<RoleMappingPage> getRoleMappingPageObjectByRoleId(String roleId);
    public abstract Map<String, Object> delete(Long id);
    public abstract boolean validationIsExistCheck(String roleId, Long pageId);
}
