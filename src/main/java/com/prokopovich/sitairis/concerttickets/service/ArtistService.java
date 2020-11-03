package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Artist;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> getAllArtist(Sort sort);
    void addNewArtist(Artist artist);
    void deleteArtist(Artist artist);
    void editArtist(Artist artist);
    Optional<Artist> getByArtistId(int id);
}
