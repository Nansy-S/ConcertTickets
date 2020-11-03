package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.dto.NewArtistDto;
import com.prokopovich.sitairis.concerttickets.entity.Artist;
import com.prokopovich.sitairis.concerttickets.exceptions.NoSuchEntityException;
import com.prokopovich.sitairis.concerttickets.service.ArtistService;
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
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(value = {"/artistList"})
    public ModelAndView artistList(Model model) {
        List<Artist> artists = artistService.getAllArtist(Sort.by(Sort.Direction.ASC, "name"));
        log.info("artist List" + artists);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("artistList");
        model.addAttribute("artists", artists);
        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/artistList was called");
        return modelAndView;
    }

    @GetMapping(value = {"/addArtist"})
    public  ModelAndView showAddArtistPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addArtist");
        NewArtistDto artistForm = new NewArtistDto();
        model.addAttribute("artistForm", artistForm);
        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/addArtist - GET was called" + artistForm);
        return modelAndView;
    }

    @PostMapping(value = {"/addArtist"})
    public ModelAndView saveArtist(Model model, //
                                 @Valid @ModelAttribute("artistForm") NewArtistDto artistDto,
                                 Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("/addArtist - POST was called" + artistDto);
        if (errors.hasErrors()) {
            modelAndView.setViewName("addArtist");
            model.addAttribute("currentUser", MainController.currentUser);
        }
        else {
            modelAndView.setViewName("artistList");
            System.out.println(artistDto);

            int id = artistDto.getArtistId();
            String name = artistDto.getName();
            String musicStyle = artistDto.getMusicStyle();
            String country = artistDto.getCountry();
            int foundationYear = artistDto.getFoundationYear();
            Artist newArtist = new Artist(id, name, musicStyle, country, foundationYear);
            artistService.addNewArtist(newArtist);
            model.addAttribute("artists",  artistService.getAllArtist(Sort.by(Sort.Direction.ASC, "name")));
            model.addAttribute("currentUser", MainController.currentUser);
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editArtist/{artistId}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("artistId") int id) throws NoSuchEntityException {
        Artist artist = artistService.getByArtistId(id).orElseThrow(()-> new NoSuchEntityException("Artist not found") );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editArtist");
        modelAndView.addObject("currentUser", MainController.currentUser);
        modelAndView.addObject("artist", artist);
        log.info("/editArtist was called");
        return modelAndView;
    }

    @RequestMapping(value = "/editArtist", method = RequestMethod.POST)
    public ModelAndView editArtist( @Valid @ModelAttribute("artist") NewArtistDto artist, Errors errors) {
        log.info("/editArtist - POST was called" + artist);
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("editArtist");
            modelAndView.addObject("currentUser", MainController.currentUser);
        }
        Artist newArtist = new Artist(
                artist.getArtistId(),
                artist.getName(),
                artist.getMusicStyle(),
                artist.getCountry(),
                artist.getFoundationYear());
        artistService.addNewArtist(newArtist);
        modelAndView.setViewName("redirect:/artistList");
        modelAndView.addObject("currentUser", MainController.currentUser);
        return modelAndView;
    }


    @RequestMapping(value = "/deleteArtist/{artistId}", method = RequestMethod.GET)
    public ModelAndView deleteArtist(@PathVariable("artistId") int id) throws NoSuchEntityException {
        Artist artist = artistService.getByArtistId(id).orElseThrow(()-> new NoSuchEntityException("Artist not found") );
        artistService.deleteArtist(artist);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/artistList");
        return modelAndView;
    }
}
