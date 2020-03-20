package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Role;
import me.dinosauruncle.msa.account.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@Service
@Transactional
public class RoleServiceImpl extends RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Map<String, Object> save(Role role) {
        parameterMap.put("role", role);
        roleRepository.save(role);
        return parameterMap;
    }

    @Override
    public Map<String, Object> getRole(String roleId) {
        Role role = roleRepository.findById(roleId).get();
        parameterMap.put("role", role);
        return parameterMap;
    }
}
