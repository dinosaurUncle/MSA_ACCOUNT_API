package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.Role;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class RoleService extends DefaultService {
    public abstract Map<String, Object> save(Role role, String... args);
    public abstract Map<String, Object> update(Role role, String... args);
    public abstract Map<String, Object> delete(String roleId, String... args);
    public abstract Map<String, Object> getRoles();
    public abstract Map<String, Object> getRole(String roleId);

}
