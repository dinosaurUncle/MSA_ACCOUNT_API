package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MsaAccountRepository extends JpaRepository<Account, String> {
/*
    @Query("SELECT a.id FROM ACCOUNT a  WHERE a.name = :name AND a.email = :email")
    String selectId(@Param("name") String name, @Param("email") String email);

    @Query("SELECT a FROM ACCOUNT a WHERE a.id = :id AND a.password = :password")
    Account login(@Param("id") String id,
                                 @Param("password") String password);

 */

}
