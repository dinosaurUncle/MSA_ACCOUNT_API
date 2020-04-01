package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.Role;
import me.dinosauruncle.service.portal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Transactional
public class RoleServiceImpl extends RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Map<String, Object> saveAndUpdate(Role role) {
        parameterMap.put("role", role);
        roleRepository.save(role);
        return parameterMap;
    }

    @Override
    public Map<String, Object> save(Role role, String... args) {
        return saveAndUpdate(role);
    }

    @Override
    public Map<String, Object> update(Role role, String... args) {
        return saveAndUpdate(role);
    }

    @Override
    public Map<String, Object> delete(String roleId, String... args) {
        Role role = roleRepository.findById(roleId).get();
        parameterMap.put("role", role);
        roleRepository.delete(role);
        return parameterMap;
    }

    @Override
    public Map<String, Object> getRoles() {
        List<Role> roles = roleRepository.findAll();
        parameterMap.put("roles", roles);
        return parameterMap;
    }

    @Override
    public Map<String, Object> getRole(String roleId) {
        Role role = roleRepository.findById(roleId).get();
        parameterMap.put("role", role);
        return parameterMap;
    }
}
