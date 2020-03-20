package me.dinosauruncle.msa.account.service;

import me.dinosauruncle.msa.account.domain.AccountMappingRole;
import me.dinosauruncle.msa.account.repository.AccountMappingPageRepository;
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
    AccountMappingPageRepository accountMappingPageRepository;

    @Override
    public Map<String, Object> save(AccountMappingRole accountMappingRole) {
        if (validationIsExistCheck(accountMappingRole.getAccount().getAccountId(), accountMappingRole.getRole().getRoleId())){
            String errorMessage = "accountId, roleId 값 중복입니다";
            logger.error(errorMessage);
            parameterMap.put("message", errorMessage);
            return parameterMap;
        }
        parameterMap.put("accountMappingRole", accountMappingRole);
        accountMappingPageRepository.save(accountMappingRole);
        return parameterMap;
    }

    @Override
    public List<AccountMappingRole> getAccountMappingRoleByAccountId(String accountId) {
        return accountMappingPageRepository.selectByAccountId(accountId);
    }

    @Override
    public Map<String, Object> delete(Long id) {
        return null;
    }

    @Override
    public boolean validationIsExistCheck(String accountId, String roleId) {
        return accountMappingPageRepository.validationCheck(accountId, roleId).size() >0 ;
    }
}
