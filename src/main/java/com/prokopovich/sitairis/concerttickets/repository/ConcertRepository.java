package com.prokopovich.sitairis.concerttickets.repository;

import com.prokopovich.sitairis.concerttickets.entity.Concert;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertRepository extends CrudRepository<Concert, Long> {
    List<Concert> findAll(Sort sort);
    Optional<Concert> findAllByConcertId(int id);
    List<Concert> findAllByManagerId(int id);

    @Query("SELECT c FROM Concert c JOIN Artist a ON c.artistId=a.artistId " +
                                   "JOIN Location l ON c.locationId=l.locationId " +
            "WHERE a.musicStyle LIKE %:style% AND l.locationName LIKE %:locationName% " +
            "AND c.concertDate LIKE %:concertDate% AND a.country LIKE %:artistCountry%")
    List<Concert> getByStyleMusicAndLocationAndDateAndCountry(@Param("style") String style,
                                                              @Param("locationName") String locationName,
                                                              @Param("concertDate") String concertDate,
                                                              @Param("artistCountry") String artistCountry);
}