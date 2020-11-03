package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Location;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> getAllLocation(Sort sort);
    void addNewLocation(Location location);
    void deleteLocation(Location location);
    void editLocation(Location location);
    Optional<Location> getByLocationId(int id);
}
