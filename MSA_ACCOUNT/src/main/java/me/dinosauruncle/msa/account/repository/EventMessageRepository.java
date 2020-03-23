package me.dinosauruncle.msa.account.repository;

import me.dinosauruncle.msa.account.domain.EventMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventMessageRepository extends JpaRepository<EventMessage, Long> {
}
