package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.entity.Client;
import com.prokopovich.sitairis.concerttickets.entity.Concert;
import com.prokopovich.sitairis.concerttickets.entity.Manager;
import com.prokopovich.sitairis.concerttickets.entity.Ticket;
import com.prokopovich.sitairis.concerttickets.exceptions.NoSuchEntityException;
import com.prokopovich.sitairis.concerttickets.service.ConcertService;
import com.prokopovich.sitairis.concerttickets.service.ManagerService;
import com.prokopovich.sitairis.concerttickets.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class ManagerController {
    private final ManagerService managerService;
    private final ConcertService concertService;
    private final TicketService ticketService;

    @Autowired
    public ManagerController(ManagerService managerService, TicketService ticketService, ConcertService concertService) {
        this.managerService = managerService;
        this.concertService = concertService;
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/infoManager/{userId}", method = RequestMethod.GET)
    public ModelAndView showInfoManager(@PathVariable("userId") int id) throws NoSuchEntityException {
        Manager manager = managerService.getByManagerId(id).orElseThrow(()-> new NoSuchEntityException("User not found"));
        List<Concert> concerts = concertService.getByManagerId(manager.getManagerId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("infoManager");
        modelAndView.addObject("currentUser", MainController.currentUser);
        modelAndView.addObject("manager", manager);
        modelAndView.addObject("concerts", concerts);
        log.info("/infoManager was called");
        return modelAndView;
    }
}
