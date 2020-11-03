package com.prokopovich.sitairis.concerttickets.repository;

import com.prokopovich.sitairis.concerttickets.entity.Location;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll(Sort sort);
    Optional<Location> findAllByLocationId(int id);
}
