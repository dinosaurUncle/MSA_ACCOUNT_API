package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.RoleMappingPage;
import me.dinosauruncle.service.portal.repository.RoleMappingPageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional
@Service
public class RoleMappingPageServiceImpl extends RoleMappingPageService {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    RoleMappingPageRepository roleMappingPageRepository;

    @Override
    public Map<String, Object> save(RoleMappingPage roleMappingPage, String... args) {

        if (validationIsExistCheck(roleMappingPage.getRole().getRoleId(), roleMappingPage.getPage().getPageId())){
            String errorMessage = "roleId, pageId 값 중복입니다";
            logger.error(errorMessage);
            parameterMap.put("message", errorMessage);
            return parameterMap;
        }
        parameterMap.put("roleMappingPage", roleMappingPage);
        roleMappingPageRepository.save(roleMappingPage);
        return parameterMap;
    }

    @Override
    public List<RoleMappingPage> getRoleMappingPageObjectByRoleId(String roleId) {
        return roleMappingPageRepository.selectByRoleId(roleId);
    }

    @Override
    public Map<String, Object> delete(Long id, String... args) {
        RoleMappingPage roleMappingPage = roleMappingPageRepository.findById(id).get();
        parameterMap.put("roleMappingPage", roleMappingPage);
        roleMappingPageRepository.delete(roleMappingPage);
        return parameterMap;
    }

    @Override
    public RoleMappingPage selectByRoleIdAndPageId(String roleId, Long pageId) {
        return roleMappingPageRepository.selectByRoleIdAndPageId(roleId, pageId);
    }

    @Override
    public boolean validationIsExistCheck(String roleId, Long pageId) {
        return roleMappingPageRepository.selectByRoleIdAndPageId(roleId, pageId) != null;
    }

}
