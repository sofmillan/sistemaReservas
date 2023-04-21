package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.exceptions.DataAlreadyExistsException;
import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client){
        Optional<Client> optionalClient = this.clientRepository.findById(client.getClientId());
        if(optionalClient.isPresent()){
            throw new DataAlreadyExistsException("Client with id "+client.getClientId()+" already exists.");
        }
        if(client.getClientId()==null){
            throw new InvalidDataException("Id cannot be null");
        }
        if(client.getLastName()==null){
            throw new InvalidDataException("Last name cannot be null");
        }
        if(client.getName()==null){
            throw new InvalidDataException("Name cannot be null");
        }
        return this.clientRepository.save(client);
    }
}
