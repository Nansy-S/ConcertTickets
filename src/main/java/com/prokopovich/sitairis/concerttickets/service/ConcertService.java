package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Concert;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ConcertService {
    List<Concert> getAllConcert(Sort sort);
    void addNewConcert(Concert concert);
    void deleteConcert(Concert concert);
    void editConcert(Concert concert);
    Optional<Concert> getByConcertId(int id);
    List<Concert> getByManagerId(int id);
    List<Concert> getByStyleMusicAndLocationAndDate(String styleMusic, String location, String date, String artistCountry);

}
