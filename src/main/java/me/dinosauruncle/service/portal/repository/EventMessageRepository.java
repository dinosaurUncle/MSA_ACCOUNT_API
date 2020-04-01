package me.dinosauruncle.service.portal.repository;

import me.dinosauruncle.service.portal.domain.EventMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventMessageRepository extends JpaRepository<EventMessage, Long> {

    @Query(value = "SELECT em.* FROM event_message em WHERE em.account_id = :accountId ORDER BY em.date DESC", nativeQuery = true)
    List<EventMessage> selectByAccountId(@Param("accountId") String accountId);

    @Query(value = "select count(*) as messageCount from event_message em where em.account_id = :accountId and em.is_check=false", nativeQuery = true)
    int nonCheckedCount(@Param("accountId") String accountId);
}
