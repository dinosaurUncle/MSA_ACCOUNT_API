package me.dinosauruncle.service.portal.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    private String roleId;

    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "role")
    private List<RoleMappingPage> roleMappingPages = new ArrayList<RoleMappingPage>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "role")
    private List<AccountMappingRole> accountMappingRoles = new ArrayList<AccountMappingRole>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
