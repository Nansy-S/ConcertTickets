package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTicket();
    void addNewTicket(Ticket ticket);
    void deleteTicket(Ticket ticket);
    List<Ticket> getAllByConcertId(int id);
    List<Ticket> getAllByClientId(int id);
}
