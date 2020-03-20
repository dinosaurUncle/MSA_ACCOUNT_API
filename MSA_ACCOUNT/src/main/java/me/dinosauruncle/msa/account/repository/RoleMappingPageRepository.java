package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.RoleMappingPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleMappingPageRepository extends JpaRepository<RoleMappingPage, Long> {

    @Query(value = "SELECT rmp.* FROM role_mapping_page rmp  WHERE rmp.role_id = :roleId"
            , nativeQuery = true)
    List<RoleMappingPage> selectByRoleId(@Param("roleId") String roleId);

    @Query(value = "SELECT rmp.* FROM role_mapping_page rmp  WHERE rmp.role_id = :roleId AND rmp.page_id =:pageId"
            , nativeQuery = true)
    List<RoleMappingPage> validataionCheck(@Param("roleId") String roleId, @Param("pageId") Long PageId);
}
