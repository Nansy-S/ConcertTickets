package com.prokopovich.sitairis.concerttickets.repository;

import com.prokopovich.sitairis.concerttickets.entity.TicketAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketAccountRepository extends CrudRepository<TicketAccount, Long> {
    List<TicketAccount> findAll();
    Optional<TicketAccount> findAllByTicketAccountId(int id);
}
