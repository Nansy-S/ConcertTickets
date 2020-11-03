package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Ticket;
import com.prokopovich.sitairis.concerttickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }

    public void addNewTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Ticket ticket){
        ticketRepository.delete(ticket);
    }

    public List<Ticket> getAllByConcertId(int id){
        return ticketRepository.findAllByConcertId(id);
    }

    public List<Ticket> getAllByClientId(int id){
        return ticketRepository.findAllByClientId(id);
    }
}
