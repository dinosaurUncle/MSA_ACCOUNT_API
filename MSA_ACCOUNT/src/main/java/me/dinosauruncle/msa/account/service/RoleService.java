package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Role;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class RoleService extends DefaultService {
    public abstract Map<String, Object> save(Role role);
    public abstract Map<String, Object> getRole(String roleId);

}
