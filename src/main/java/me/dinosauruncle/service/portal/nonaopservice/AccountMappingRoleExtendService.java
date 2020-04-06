package me.dinosauruncle.service.portal.nonaopservice;

import me.dinosauruncle.service.portal.domain.AccountMappingRole;
import me.dinosauruncle.service.portal.domain.Role;
import me.dinosauruncle.service.portal.service.AccountMappingRoleService;
import me.dinosauruncle.service.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<String, Object> getHighlightData(String accountId){
        List<AccountMappingRole> list = accountMappingRoleService.getAccountMappingRoleByAccountId(accountId);
        Map<String, Object>resultMap = new HashMap<String, Object>();
        resultMap.put("accountRoleMappingInfo", list);
        Map<String, Object> roles = roleService.getRoles();
        List<Role> roleList =(List<Role>)roles.get("roles");
        Map<String, String> roleIdMap = new HashMap<String, String>();
        roleList.forEach(role -> {
            roleIdMap.put(role.getRoleId(), role.getRoleId());
        });
        list.forEach(accountMappingRole -> {
            roleIdMap.remove(accountMappingRole.getRole().getRoleId());
        });
        List<String> resultList = null;
        if (!(roleIdMap.size() == 0)){
            resultList = roleIdMap.keySet().stream().collect(Collectors.toList());
            resultMap.put("hignLightData", resultList);
        } else {
            resultMap.put("hignLightData", new String[]{"추가 할 Role이 존재하지 않습니다"});
        }
        return resultMap;
    }
}
