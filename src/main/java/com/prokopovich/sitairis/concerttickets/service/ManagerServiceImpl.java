package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Manager;
import com.prokopovich.sitairis.concerttickets.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAllManager() {
        return managerRepository.findAll();
    }

    public  void addNewManager(Manager manager){
        managerRepository.save(manager);
    }

    public Optional<Manager> getByManagerId(int id) {
        return managerRepository.findAllByManagerId(id);
    }

}
