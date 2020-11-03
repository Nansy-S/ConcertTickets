package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.dto.*;
import com.prokopovich.sitairis.concerttickets.entity.*;
import com.prokopovich.sitairis.concerttickets.exceptions.NoSuchEntityException;
import com.prokopovich.sitairis.concerttickets.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class ConcertController {
    private final ConcertService concertService;
    private final ArtistService artistService;
    private final LocationService locationService;
    private final ManagerService managerService;
    private final TicketAccountService ticketAccountService;

    @Autowired
    public ConcertController(ConcertService concertService, ArtistService artistService,
                             LocationService locationService, ManagerService managerService,
                             TicketAccountService ticketAccountService) {
        this.concertService = concertService;
        this.artistService = artistService;
        this.locationService = locationService;
        this.managerService = managerService;
        this.ticketAccountService = ticketAccountService;
    }

    @GetMapping(value = {"/concertList"})
    public ModelAndView concertList(Model model) {
        List<Concert> concerts = concertService.getAllConcert(Sort.by(Sort.Direction.ASC, "concertDate"));
        List<TicketAccount> ticketAccounts = ticketAccountService.getAllTicketAccount();
        List<Location> locations = locationService.getAllLocation(Sort.by(Sort.Direction.ASC, "locationName"));
        ConcertFilter concertFilter = new ConcertFilter();
        log.info("concert List" + concerts);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("concertList");
        model.addAttribute("concerts", concerts);
        model.addAttribute("ticketAccounts", ticketAccounts);
        model.addAttribute("currentUser", MainController.currentUser);
        model.addAttribute("locations", locations);
        model.addAttribute("ConcertFilter", concertFilter);
        log.info("/concertList was called");
        return modelAndView;
    }

    @PostMapping(value = {"/concertList"})
    public ModelAndView filterConcert(Model model, @Valid @ModelAttribute("ConcertFilter") ConcertFilter concertFilter, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if(concertFilter.getMusicStyle() == null) concertFilter.setMusicStyle("");
        if(concertFilter.getLocation() == null) concertFilter.setLocation("");
        if(concertFilter.getDate() == null) concertFilter.setDate("");
        if(concertFilter.getArtistCountry() == null) concertFilter.setArtistCountry("");
        List<Concert> concerts = concertService.getByStyleMusicAndLocationAndDate(
                concertFilter.getMusicStyle(), concertFilter.getLocation(), concertFilter.getDate(), concertFilter.getArtistCountry());

        List<TicketAccount> ticketAccounts = ticketAccountService.getAllTicketAccount();
        List<Location> locations = locationService.getAllLocation(Sort.by(Sort.Direction.ASC, "locationName"));
        model.addAttribute("concerts", concerts);
        model.addAttribute("ticketAccounts", ticketAccounts);
        model.addAttribute("currentUser", MainController.currentUser);
        model.addAttribute("locations", locations);
        model.addAttribute("ConcertFilter", concertFilter);

        modelAndView.addObject("concerts", concerts);
        return modelAndView;
    }


    @GetMapping(value = {"/concertListTable"})
    public ModelAndView concertListTable(Model model) {
        List<Concert> concerts = concertService.getAllConcert(Sort.by(Sort.Direction.ASC, "concertDate"));
        List<TicketAccount> ticketAccounts = ticketAccountService.getAllTicketAccount();
        log.info("concert List" + concerts);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("concertListTable");
        model.addAttribute("currentUser", MainController.currentUser);
        model.addAttribute("concerts", concerts);
        model.addAttribute("ticketAccounts", ticketAccounts);
        log.info("/concertListTable was called");
        return modelAndView;
    }

    @RequestMapping(value = "/displayConcert/{concertId}", method = RequestMethod.GET)
    public ModelAndView displayConcert(@PathVariable("concertId") int id) throws NoSuchEntityException {
        Concert concert = concertService.getByConcertId(id).orElseThrow(()-> new NoSuchEntityException("Concert not found") );
        ModelAndView modelAndView = new ModelAndView();
        TicketDto ticketForm = new TicketDto();
        modelAndView.setViewName("displayConcert");
        modelAndView.addObject("concert", concert);
        modelAndView.addObject("ticketForm", ticketForm);
        modelAndView.addObject("currentUser", MainController.currentUser);
        log.info("/editConcert was called");
        return modelAndView;
    }

    @GetMapping(value = {"/addConcert"})
    public  ModelAndView showAddConcertPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addConcert");
        NewConcertDto concertForm = new NewConcertDto();
        model.addAttribute("concertForm", concertForm);

        List<Artist> artists = artistService.getAllArtist(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("artists", artists);

        List<Location> locations = locationService.getAllLocation(Sort.by(Sort.Direction.ASC, "locationName"));
        model.addAttribute("locations", locations);

        TicketAccount ticketAccount = new TicketAccount();
        model.addAttribute("ticketAccount", ticketAccount);

        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/concertForm - GET was called" + concertForm);
        return modelAndView;
    }

     @PostMapping(value = {"/addConcert"})
     public ModelAndView saveConcert(Model model, //
                                     @Valid @ModelAttribute("concertForm") NewConcertDto concertDto, Errors errorsConcert,
                                     @Valid @ModelAttribute("artists") Artist artist, Errors errorsArtist,
                                     @Valid @ModelAttribute("locations") Location location, Errors errorsLocation,
                                     @Valid @ModelAttribute("ticketAccount") NewTicketAccountDto ticketAccount, Errors errorsTicketAccount) throws NoSuchEntityException {
         ModelAndView modelAndView = new ModelAndView();
         log.info("/addConcert - POST was called" + concertDto);
         if (errorsConcert.hasErrors() || errorsArtist.hasErrors() || errorsLocation.hasErrors() || errorsTicketAccount.hasErrors()) {
             modelAndView.setViewName("addConcert");
             model.addAttribute("currentUser", MainController.currentUser);
             model.addAttribute("message", "Некорректные данные");
         }
         else {
             int managerId = MainController.currentUser.getUserId();
             Manager managerInfo = managerService.getByManagerId(managerId).orElseThrow(()-> new NoSuchEntityException("User not found"));
             int ticketAccountId = (int) (Math.random() * 100000);
             TicketAccount newTicketAccount = new TicketAccount(
                     ticketAccountId,
                     ticketAccount.getNumberFanzone(),
                     ticketAccount.getPriceFanzone(),
                     0,
                     ticketAccount.getNumberDancefloor(),
                     ticketAccount.getPriceDancefloor(),
                     0,
                     ticketAccount.getNumberTribune(),
                     ticketAccount.getPriceTribune(),
                     0);
             if(nameFilePoster.equals(""))
                 nameFilePoster = "no-photo.png";
             Concert newConcert = new Concert(
                     concertDto.getConcertId(),
                     concertDto.getConcertDate(),
                     concertDto.getConcertTime(),
                     concertDto.getNameFilePoster(),
                     artist.getArtistId(),
                     artist,
                     location.getLocationId(),
                     location,
                     MainController.currentUser.getUserId(),
                     managerInfo,
                     ticketAccountId,
                     newTicketAccount);
             Manager newManager = new Manager(
                     MainController.currentUser.getUserId(),
                     managerInfo.getUserInfo(),
                     managerInfo.getPhone(),
                     managerInfo.getEventAgency(),
                     managerInfo.getConcertsNumber() + 1);
             ticketAccountService.addNewTicketAccount(newTicketAccount);
             concertService.addNewConcert(newConcert);
             managerService.addNewManager(newManager);
             modelAndView.setViewName("redirect:/concertListTable");
             model.addAttribute("concerts",  concertService.getAllConcert(Sort.by(Sort.Direction.ASC, "concertDate")));
             model.addAttribute("currentUser", MainController.currentUser);
             return modelAndView;
         }
         return modelAndView;
     }

    @RequestMapping(value = "/editConcert/{concertId}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("concertId") int id) throws NoSuchEntityException {
        Concert concert = concertService.getByConcertId(id).orElseThrow(()-> new NoSuchEntityException("Concert not found") );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editConcert");
        modelAndView.addObject("concert", concert);

        List<Artist> artists = artistService.getAllArtist(Sort.by(Sort.Direction.ASC, "name"));
        modelAndView.addObject("artists", artists);

        List<Location> locations = locationService.getAllLocation(Sort.by(Sort.Direction.ASC, "locationName"));
        modelAndView.addObject("locations", locations);

        modelAndView.addObject("currentUser", MainController.currentUser);
        TicketAccount ticketAccount = ticketAccountService.getByTicketAccountId(concert.getTicketAccountId()).
                orElseThrow(()-> new NoSuchEntityException("TicketAccount not found") );
        modelAndView.addObject("ticketAccount", ticketAccount);
        log.info("/editConcert was called");
        return modelAndView;
    }

    @RequestMapping(value = "/editConcert", method = RequestMethod.POST)
    public ModelAndView editConcert( @Valid @ModelAttribute("concert") Concert concert, Errors errors) throws NoSuchEntityException {
        log.info("/editConcert - POST was called" + concert);
        TicketAccount ticketAccount = concert.getTicketAccountInfo();
        ticketAccount.setTicketAccountId(concert.getTicketAccountId());
        concert.setTicketAccountInfo(ticketAccount);
        concert.setManagerId(MainController.currentUser.getUserId());
        Manager managerInfo = managerService.getByManagerId(MainController.currentUser.getUserId()).orElseThrow(()-> new NoSuchEntityException("User not found"));
        concert.setManagerInfo(managerInfo);

        ticketAccountService.addNewTicketAccount(ticketAccount);
        concertService.addNewConcert(concert);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/concertListTable");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteConcert/{concertId}", method = RequestMethod.GET)
    public ModelAndView deleteConcert(@PathVariable("concertId") int id) throws NoSuchEntityException {
        Concert concert = concertService.getByConcertId(id).orElseThrow(()-> new NoSuchEntityException("Concert not found") );
        TicketAccount ticketAccount = ticketAccountService.getByTicketAccountId(concert.getTicketAccountId()).
                orElseThrow(()-> new NoSuchEntityException("TicketAccount not found") );
        concertService.deleteConcert(concert);
        ticketAccountService.deleteTicketAccount(ticketAccount);

        Manager manager = managerService.getByManagerId(MainController.currentUser.getUserId()).orElseThrow(()-> new NoSuchEntityException("User not found"));
        Manager newManager = new Manager(manager.getManagerId(), manager.getUserInfo(), manager.getPhone(),
                manager.getEventAgency(), manager.getConcertsNumber() - 1);
        managerService.addNewManager(newManager);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/concertListTable");
        return modelAndView;
    }
}
