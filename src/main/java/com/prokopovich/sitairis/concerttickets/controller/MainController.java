package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.entity.Concert;
import com.prokopovich.sitairis.concerttickets.entity.User;
import com.prokopovich.sitairis.concerttickets.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class MainController {

    private final ConcertService concertService;
    private final UserAppService userService;
    public static User currentUser;

    @Autowired
    public MainController(ConcertService concertService, UserAppService userService) {
        this.concertService = concertService;
        this.userService = userService;
        currentUser = new User();
    }

    static User getCurrentUser(UserAppService userService){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLogin = authentication.getName();
        User userTmp = userService.getByLogin(currentLogin);
        if(userTmp != null) currentUser = userTmp;
        return currentUser;
    }

    @Value("${welcome.message}")
    private String message;

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model) {
        model.addAttribute("currentUser", getCurrentUser(userService));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("index was called");
        List<Concert> concerts = concertService.getAllConcert(Sort.by(Sort.Direction.ASC, "concertDate"));
        model.addAttribute("concerts", concerts);
        return modelAndView;
    }
}
