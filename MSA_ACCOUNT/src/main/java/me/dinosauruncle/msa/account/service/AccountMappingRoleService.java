package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.AccountMappingRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class AccountMappingRoleService extends DefaultService {
    public abstract Map<String, Object> save(AccountMappingRole accountMappingRole);
    public abstract List<AccountMappingRole> getAccountMappingRoleByAccountId(String accountId);
    public abstract Map<String, Object> delete(Long id);
    public abstract boolean validationIsExistCheck(String accountId, String roleId);
}
