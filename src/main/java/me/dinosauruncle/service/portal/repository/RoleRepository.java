package me.dinosauruncle.service.portal.repository;

import me.dinosauruncle.service.portal.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
