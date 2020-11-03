package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.dto.NewClientDto;
import com.prokopovich.sitairis.concerttickets.dto.NewUserDto;
import com.prokopovich.sitairis.concerttickets.entity.Client;
import com.prokopovich.sitairis.concerttickets.entity.Ticket;
import com.prokopovich.sitairis.concerttickets.entity.User;
import com.prokopovich.sitairis.concerttickets.exceptions.NoSuchEntityException;
import com.prokopovich.sitairis.concerttickets.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class ClientController {
    private final ClientService clientService;
    private final ConcertService concertService;
    private final TicketService ticketService;

    @Autowired
    public ClientController(ClientService clientService, TicketService ticketService, ConcertService concertService) {
        this.clientService = clientService;
        this.concertService = concertService;
        this.ticketService = ticketService;
    }

    @GetMapping(value = {"/registration"})
    public  ModelAndView showRegistrationPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        NewClientDto clientForm = new NewClientDto();
        model.addAttribute("clientForm", clientForm);
        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/registration - GET was called" + clientForm);
        return modelAndView;
    }

    @RequestMapping(value = "/infoClient/{userId}", method = RequestMethod.GET)
    public ModelAndView showInfoClient(@PathVariable("userId") int id) throws NoSuchEntityException {
        Client client = clientService.getByClientId(id).orElseThrow(()-> new NoSuchEntityException("User not found"));
        List<Ticket> tickets = ticketService.getAllByClientId(client.getClientId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("infoClient");
        modelAndView.addObject("currentUser", MainController.currentUser);
        modelAndView.addObject("tickets", tickets);
        modelAndView.addObject("client", client);

        log.info("/infoClient was called");
        return modelAndView;
    }
}
