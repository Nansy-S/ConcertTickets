package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Client;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClient();
    void addNewClient(Client client);
    Optional<Client> getByClientId(int id);
}
