package me.dinosauruncle.service.portal.controller;

import me.dinosauruncle.service.portal.domain.Role;
import me.dinosauruncle.service.portal.nonaopservice.CommonService;
import me.dinosauruncle.service.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CommonService commonService;

    @GetMapping("/admin")
    public Map<String, Object> getRoles() { return roleService.getRoles();}

    @PostMapping("/admin/{accountId}")
    public Map<String, Object> saveByAdmin(@PathVariable("accountId") String accountId,
                                           @RequestBody Role role) {
        roleService.save(role, commonService.SerializationKeyAndValue(1, accountId),
                commonService.SerializationKeyAndValue(3, role.getRoleId()));
        return getRoles();
    }

    @PutMapping("/admin/{accountId}")
    public Map<String, Object> updateByAdmin(@PathVariable("accountId") String accountId,
                                             @RequestBody Role role) {
        roleService.update(role, commonService.SerializationKeyAndValue(1, accountId),
                commonService.SerializationKeyAndValue(3, role.getRoleId()));
        return getRoles();
    }

    @DeleteMapping("/admin/{roleId}/{accountId}")
    public Map<String, Object> deleteByAdmin(@PathVariable("roleId") String roleId,
                                             @PathVariable("accountId") String accountId) {
        roleService.delete(roleId, commonService.SerializationKeyAndValue(1, accountId),
                commonService.SerializationKeyAndValue(3, roleId));
        return getRoles();
    }
}
