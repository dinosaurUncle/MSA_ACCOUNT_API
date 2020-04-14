package me.dinosauruncle.service.portal.nonaopservice;

import me.dinosauruncle.service.portal.domain.AccountMappingRole;
import me.dinosauruncle.service.portal.domain.Role;
import me.dinosauruncle.service.portal.service.AccountMappingRoleService;
import me.dinosauruncle.service.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountMappingRoleExtendService {

    @Autowired
    private AccountMappingRoleService accountMappingRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    CommonService commonService;

    public Map<String, Object> getHighlightData(String accountId){
        List<AccountMappingRole> list = accountMappingRoleService.getAccountMappingRoleByAccountId(accountId);
        List<Role> listInRoleList = new ArrayList<Role>();
        list.forEach(accountMappingRole -> {
            listInRoleList.add(accountMappingRole.getRole());
        });
        Map<String, Object>resultMap = new HashMap<String, Object>();
        resultMap.put("roleList", listInRoleList);
        Map<String, Object> roles = roleService.getRoles();
        List<Role> roleList =(List<Role>)roles.get("roles");
        Map<String, Role> roleIdMap = new HashMap<String, Role>();
        roleList.forEach(role -> {
            roleIdMap.put(role.getRoleId(), role);
        });
        list.forEach(accountMappingRole -> {
            roleIdMap.remove(accountMappingRole.getRole().getRoleId());
        });
        List<Role> resultList = null;
        if (!(roleIdMap.size() == 0)){
            resultList = roleIdMap.values().stream().collect(Collectors.toList());
            resultMap.put("hignLightData", resultList);
        } else {
            resultMap.put("hignLightData", new String[]{"null"});
        }
        return resultMap;
    }

    public Map<String, Object> save(String accountId, String[] roleIds, String... strings) {
        Map<String, Object> resultMap = null;
        List<AccountMappingRole> accountMappingRoles = new ArrayList<AccountMappingRole>();
        for(String roleId: roleIds){
            resultMap = accountMappingRoleService.save(accountId, roleId,
                    commonService.SerializationKeyAndValue(2, accountId),
                    commonService.SerializationKeyAndValue(3, roleId));
            accountMappingRoles.add((AccountMappingRole)resultMap.get("accountMappingRole"));
        }
        resultMap.clear();
        resultMap.put("accountMappingRoles", accountMappingRoles);
        return resultMap;
    }
}
