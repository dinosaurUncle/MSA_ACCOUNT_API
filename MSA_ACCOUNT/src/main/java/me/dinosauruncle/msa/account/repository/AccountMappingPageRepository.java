package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.AccountMappingRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountMappingPageRepository extends JpaRepository<AccountMappingRole, Long> {

    @Query(value = "SELECT amp.* FROM account_mapping_role amp  WHERE amp.account_id = :accountId"
            , nativeQuery = true)
    List<AccountMappingRole> selectByAccountId(@Param("accountId") String accountId);

    @Query(value = "SELECT amp.* FROM account_mapping_role amp  WHERE amp.account_id = :accountId AND amp.role_id = :roleId"
            , nativeQuery = true)
    List<AccountMappingRole> validationCheck(@Param("accountId") String accountId, @Param("roleId") String roleId);
}
