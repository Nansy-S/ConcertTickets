package com.prokopovich.sitairis.concerttickets.repository;

import com.prokopovich.sitairis.concerttickets.entity.Artist;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    List<Artist> findAll(Sort sort);
    Optional<Artist> findAllByArtistId(int id);
    Optional<Artist> findAllByMusicStyle(String musicStyle);
}
