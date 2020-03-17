package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
