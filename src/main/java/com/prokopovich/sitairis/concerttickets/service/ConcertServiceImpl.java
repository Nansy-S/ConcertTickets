package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Concert;
import com.prokopovich.sitairis.concerttickets.entity.User;
import com.prokopovich.sitairis.concerttickets.repository.ConcertRepository;
import com.prokopovich.sitairis.concerttickets.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;

    @Autowired
    public ConcertServiceImpl(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getAllConcert(Sort sort) {
        return concertRepository.findAll(sort);
    }

    public void addNewConcert(Concert concert){
        concertRepository.save(concert);
    }

    public void deleteConcert(Concert concert){
        concertRepository.delete(concert);
    }

    public void editConcert(Concert concert){
        concertRepository.save(concert);
    }

    public Optional<Concert> getByConcertId(int id) {
        return concertRepository.findAllByConcertId(id);
    }

    public List<Concert> getByManagerId(int id){
        return concertRepository.findAllByManagerId(id);
    }

    public List<Concert> getByStyleMusicAndLocationAndDate(String styleMusic, String location, String date, String artistCountry){
        return concertRepository.getByStyleMusicAndLocationAndDateAndCountry(styleMusic, location, date, artistCountry);
    }
}
