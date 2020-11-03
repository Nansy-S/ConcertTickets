package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.TicketAccount;

import java.util.List;
import java.util.Optional;

public interface TicketAccountService {
    List<TicketAccount> getAllTicketAccount();
    void addNewTicketAccount(TicketAccount ticketAccount);
    void deleteTicketAccount(TicketAccount ticketAccount);
    void editTicketAccount(TicketAccount ticketAccount);
    Optional<TicketAccount> getByTicketAccountId(int id);
}
