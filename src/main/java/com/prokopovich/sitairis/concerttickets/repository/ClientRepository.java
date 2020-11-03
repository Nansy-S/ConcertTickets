package com.prokopovich.sitairis.concerttickets.repository;

import com.prokopovich.sitairis.concerttickets.entity.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long> {
    List<Client> findAll();
    Optional<Client> findAllByClientId(int id);
}
