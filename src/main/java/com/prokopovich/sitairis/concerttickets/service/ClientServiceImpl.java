package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.Client;
import com.prokopovich.sitairis.concerttickets.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public  void addNewClient(Client client){
        clientRepository.save(client);
    }

    public Optional<Client> getByClientId(int id) {
        return clientRepository.findAllByClientId(id);
    }

}
