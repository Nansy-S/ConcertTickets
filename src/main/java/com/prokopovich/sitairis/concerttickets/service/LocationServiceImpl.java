package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Location;
import com.prokopovich.sitairis.concerttickets.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocation(Sort sort) {
        return locationRepository.findAll(sort);
    }

    public  void addNewLocation(Location location){
        locationRepository.save(location);
    }

    public void deleteLocation(Location location){
        locationRepository.delete(location);
    }

    public void editLocation(Location location){
        locationRepository.save(location);
    }

    public Optional<Location> getByLocationId(int id) {
        return locationRepository.findAllByLocationId(id);
    }
}
