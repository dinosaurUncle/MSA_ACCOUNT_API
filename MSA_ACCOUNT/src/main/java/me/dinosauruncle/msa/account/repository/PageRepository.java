package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.Page;
import me.dinosauruncle.msa.account.domain.RoleMappingPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    @Query(value = "SELECT p.* FROM page p  WHERE p.page_name = :pageName"
            , nativeQuery = true)
    Page selectByPageName(@Param("pageName") String pageName);
}
