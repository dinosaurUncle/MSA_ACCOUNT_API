package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
