package me.dinosauruncle.service.portal.service;

import me.dinosauruncle.service.portal.domain.AccountMappingRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class AccountMappingRoleService extends DefaultService {
    public abstract Map<String, Object> save(AccountMappingRole accountMappingRole, String... strings);
    public abstract Map<String, Object> save(String accountId, String roleId, String... strings);
    public abstract List<AccountMappingRole> getAccountMappingRoleByAccountId(String accountId);
    public abstract Map<String, Object> delete(String accountId, String roleId, String... strings);
    public abstract AccountMappingRole getAccountMappingRoleByAccountIdAndRoleId(String accountId, String roleId);
    public abstract boolean validationIsExistCheck(String accountId, String roleId);
}
