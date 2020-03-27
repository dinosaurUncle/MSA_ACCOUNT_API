package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT a.id FROM account a  WHERE a.name = :name AND a.email = :email"
    , nativeQuery = true)
    String selectId(@Param("name") String name, @Param("email") String email);

}
