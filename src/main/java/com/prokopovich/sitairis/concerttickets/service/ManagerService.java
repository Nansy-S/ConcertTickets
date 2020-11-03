package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Manager;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    List<Manager> getAllManager();
    void addNewManager(Manager manager);
    Optional<Manager> getByManagerId(int id);
}
