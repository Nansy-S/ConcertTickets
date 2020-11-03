package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Artist;
import com.prokopovich.sitairis.concerttickets.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtist(Sort sort){
        return artistRepository.findAll(sort);
    }

    public void addNewArtist(Artist artist){
        artistRepository.save(artist);
    }

    public void deleteArtist(Artist artist){
        artistRepository.delete(artist);
    }

    public void editArtist(Artist artist){
        artistRepository.save(artist);
    }

    public Optional<Artist> getByArtistId(int id){
        return artistRepository.findAllByArtistId(id);
    }
}
