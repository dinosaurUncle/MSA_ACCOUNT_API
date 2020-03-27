package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.Account;
import me.dinosauruncle.msa.account.domain.AccountMappingRole;
import me.dinosauruncle.msa.account.repository.AccountMappingRoleRepository;
import me.dinosauruncle.msa.account.repository.AccountRepository;
import me.dinosauruncle.msa.account.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional
@Service
public class AccountMappingRoleServiceImpl extends AccountMappingRoleService {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    AccountMappingRoleRepository accountMappingRoleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Map<String, Object> save(AccountMappingRole accountMappingRole, String... strings) {
        if (validationIsExistCheck(accountMappingRole.getAccount().getAccountId(), accountMappingRole.getRole().getRoleId())){
            String errorMessage = "accountId, roleId 값 중복입니다";
            logger.error(errorMessage);
            parameterMap.put("message", errorMessage);
            return parameterMap;
        }
        parameterMap.put("accountMappingRole", accountMappingRole);
        accountMappingRoleRepository.save(accountMappingRole);
        return parameterMap;
    }

    @Override
    public Map<String, Object> save(String accountId, String roleId, String... strings) {
        AccountMappingRole accountMappingRole = new AccountMappingRole();
        accountMappingRole.setAccount(accountRepository.findById(accountId).get());
        accountMappingRole.setRole(roleRepository.findById(roleId).get());
        return save(accountMappingRole, strings);
    }

    @Override
    public List<AccountMappingRole> getAccountMappingRoleByAccountId(String accountId) {
        return accountMappingRoleRepository.selectByAccountId(accountId);
    }

    @Override
    public Map<String, Object> delete(String accountId, String roleId, String... strings) {
        AccountMappingRole accountMappingRole = getAccountMappingRoleByAccountIdAndRoleId(accountId, roleId);
        parameterMap.put("accountMappingRole", accountMappingRole);
        accountMappingRoleRepository.delete(accountMappingRole);
        return parameterMap;
    }

    @Override
    public AccountMappingRole getAccountMappingRoleByAccountIdAndRoleId(String accountId, String roleId) {
        return accountMappingRoleRepository.selectByAccountIdAndRoleId(accountId, roleId);
    }

    @Override
    public boolean validationIsExistCheck(String accountId, String roleId) {
        return accountMappingRoleRepository.selectByAccountIdAndRoleId(accountId, roleId) != null ;
    }
}
