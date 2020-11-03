package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.dto.TicketDto;
import com.prokopovich.sitairis.concerttickets.entity.*;
import com.prokopovich.sitairis.concerttickets.exceptions.NoSuchEntityException;
import com.prokopovich.sitairis.concerttickets.service.ClientService;
import com.prokopovich.sitairis.concerttickets.service.ConcertService;
import com.prokopovich.sitairis.concerttickets.service.TicketAccountService;
import com.prokopovich.sitairis.concerttickets.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping
public class TicketController {
    private final TicketService ticketService;
    private final ConcertService concertService;
    private final ClientService clientService;
    private final TicketAccountService ticketAccountService;

    @Autowired
    public TicketController(TicketService ticketService, ConcertService concertService,
                            ClientService clientService, TicketAccountService ticketAccountService) {
        this.ticketService = ticketService;
        this.concertService = concertService;
        this.clientService = clientService;
        this.ticketAccountService = ticketAccountService;
    }

    @PostMapping(value = {"/displayConcert"})
    public ModelAndView buyTicket(Model model, //
                                   @Valid @ModelAttribute("ticketForm") TicketDto ticketDto,
                                   Errors errors) throws NoSuchEntityException {
        ModelAndView modelAndView = new ModelAndView();
        log.info("/buyTicket - POST was called" + ticketDto);
        int ticketId = 1234;
        int concertId = ticketDto.getConcertId();
        Concert concertInfo = concertService.getByConcertId(concertId).orElseThrow(()-> new NoSuchEntityException("Concert not found") );
        int clientId = MainController.currentUser.getUserId();
        Client clientInfo = clientService.getByClientId(clientId).orElseThrow(()-> new NoSuchEntityException("User not found") );
        String typeTicket = ticketDto.getTypeTicket();
        double priceTicket = ticketDto.getPriceTicket();
        Ticket newTicket = new Ticket(ticketId, concertId, concertInfo, clientId, clientInfo, typeTicket, priceTicket);

        System.out.println(newTicket);

        changeNumberTickets(concertId, typeTicket, clientId, "увеличить");
        ticketService.addNewTicket(newTicket);
        modelAndView.setViewName("redirect:/displayConcert/" + concertId);
        return modelAndView;
    }


    public void changeNumberTickets(int concertId, String typeTicket, int clientId, String changeType) throws NoSuchEntityException {
        Concert concert = concertService.getByConcertId(concertId).orElseThrow(()-> new NoSuchEntityException("Concert not found") );
        Client client = clientService.getByClientId(clientId).orElseThrow(()-> new NoSuchEntityException("User not found") );
        TicketAccount ticketAccount = concert.getTicketAccountInfo();
        if(changeType.equals("увеличить")) {
            client.setTicketNumber(client.getTicketNumber() + 1);
            if(typeTicket.equals("фан-зона")) ticketAccount.setNumberSoldFanzone(ticketAccount.getNumberSoldFanzone() + 1);
            else if(typeTicket.equals("танцпол")) ticketAccount.setNumberSoldDancefloor(ticketAccount.getNumberSoldDancefloor() + 1);
            else if(typeTicket.equals("трибуны")) ticketAccount.setNumberSoldTribune(ticketAccount.getNumberSoldTribune() + 1);
        }
        else if(changeType.equals("уменьшить")) {
            client.setTicketNumber(client.getTicketNumber() - 1);
            if(typeTicket.equals("фан-зона")) ticketAccount.setNumberSoldFanzone(ticketAccount.getNumberSoldFanzone() - 1);
            else if(typeTicket.equals("танцпол")) ticketAccount.setNumberSoldDancefloor(ticketAccount.getNumberSoldDancefloor() - 1);
            else if(typeTicket.equals("трибуны")) ticketAccount.setNumberSoldTribune(ticketAccount.getNumberSoldTribune() - 1);
        }
        ticketAccountService.addNewTicketAccount(ticketAccount);
        clientService.addNewClient(client);
    }
}
