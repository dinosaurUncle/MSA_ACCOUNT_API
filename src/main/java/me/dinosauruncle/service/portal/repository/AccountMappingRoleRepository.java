package me.dinosauruncle.service.portal.repository;

import me.dinosauruncle.service.portal.domain.AccountMappingRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountMappingRoleRepository extends JpaRepository<AccountMappingRole, Long> {

    @Query(value = "SELECT amp.* FROM account_mapping_role amp  WHERE amp.account_id = :accountId"
            , nativeQuery = true)
    List<AccountMappingRole> selectByAccountId(@Param("accountId") String accountId);

    @Query(value = "SELECT amp.* FROM account_mapping_role amp  WHERE amp.role_id = :roleId"
            , nativeQuery = true)
    List<AccountMappingRole> selectByRoleId(@Param("roleId") String roleId);

    @Query(value = "SELECT amp.* FROM account_mapping_role amp  WHERE amp.account_id = :accountId AND amp.role_id = :roleId"
            , nativeQuery = true)
    AccountMappingRole selectByAccountIdAndRoleId(@Param("accountId") String accountId, @Param("roleId") String roleId);

    @Query(value="DELETE FROM account_mapping_role amp WHERE WHERE amp.account_id = :accountId AND amp.role_id = :roleId ",
            nativeQuery = true)
    void deleteByAccountIdAndRoleId(@Param("accountId")String accountId, @Param("roleId")String roleId);
;}
