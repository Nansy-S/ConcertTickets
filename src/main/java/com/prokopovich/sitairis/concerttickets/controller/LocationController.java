package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.dto.NewLocationDto;
import com.prokopovich.sitairis.concerttickets.entity.Location;
import com.prokopovich.sitairis.concerttickets.exceptions.NoSuchEntityException;
import com.prokopovich.sitairis.concerttickets.service.LocationService;
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
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = {"/locationList"})
    public ModelAndView locationList(Model model) {
        List<Location> locations = locationService.getAllLocation(Sort.by(Sort.Direction.ASC, "locationName"));
        log.info("location List" + locations);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("locationList");
        model.addAttribute("locations", locations);
        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/locationList was called");
        return modelAndView;
    }

    @GetMapping(value = {"/addLocation"})
    public  ModelAndView showAddLocationPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addLocation");
        NewLocationDto locationForm = new NewLocationDto();
        model.addAttribute("locationForm", locationForm);
        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/addLocation - GET was called" + locationForm);
        return modelAndView;
    }

    @PostMapping(value = {"/addLocation"})
    public ModelAndView saveLocation(Model model, //
                                 @Valid @ModelAttribute("locationForm") NewLocationDto locationDto, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("/addLocation - POST was called" + locationDto);
        if (errors.hasErrors()) {
            modelAndView.setViewName("addLocation");
        }
        else {
            modelAndView.setViewName("locationList");
            Location newLocation = new Location(
                    locationDto.getLocationId(),
                    locationDto.getLocationName(),
                    locationDto.getAddress());
            locationService.addNewLocation(newLocation);
            model.addAttribute("locations",  locationService.getAllLocation(Sort.by(Sort.Direction.ASC, "locationName")));
            model.addAttribute("currentUser", MainController.currentUser);
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editLocation/{locationId}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("locationId") int id) throws NoSuchEntityException {
        Location location = locationService.getByLocationId(id).orElseThrow(()-> new NoSuchEntityException("Location not found") );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editLocation");
        modelAndView.addObject("location", location);
        modelAndView.addObject("currentUser", MainController.currentUser);
        log.info("/editLocation was called");
        return modelAndView;
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public ModelAndView editLocation( @Valid @ModelAttribute("location") Location location, Errors errors) {
        log.info("/editLocation - POST was called" + location);
        locationService.addNewLocation(location);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/locationList");
        return modelAndView;
    }


    @RequestMapping(value = "/deleteLocation/{locationId}", method = RequestMethod.GET)
    public ModelAndView deleteLocation(@PathVariable("locationId") int id) throws NoSuchEntityException {
        Location location = locationService.getByLocationId(id).orElseThrow(()-> new NoSuchEntityException("Location not found") );
        locationService.deleteLocation(location);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/locationList");
        return modelAndView;
    }
}
