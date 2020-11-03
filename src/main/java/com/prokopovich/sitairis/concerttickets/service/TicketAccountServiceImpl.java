package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.TicketAccount;
import com.prokopovich.sitairis.concerttickets.repository.TicketAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketAccountServiceImpl implements TicketAccountService{
    private final TicketAccountRepository ticketAccountRepository;

    @Autowired
    public TicketAccountServiceImpl(TicketAccountRepository ticketAccountRepository) {
        this.ticketAccountRepository = ticketAccountRepository;
    }

    public List<TicketAccount> getAllTicketAccount() {
        return ticketAccountRepository.findAll();
    }

    public void addNewTicketAccount(TicketAccount ticketAccount){
        ticketAccountRepository.save(ticketAccount);
    }

    public void deleteTicketAccount(TicketAccount ticketAccount){
        ticketAccountRepository.delete(ticketAccount);
    }

    public void editTicketAccount(TicketAccount ticketAccount){
        ticketAccountRepository.save(ticketAccount);
    }

    public Optional<TicketAccount> getByTicketAccountId(int id) {
        return ticketAccountRepository.findAllByTicketAccountId(id);
    }
}
