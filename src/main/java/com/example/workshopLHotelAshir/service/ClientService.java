package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client crear(Client client){
        if(client.getApellido()==null|| client.getNombre()==null){
            throw new InvalidDataException(("Nombre y apellido no pueden ser nulos"));
        }
        this.clientRepository.save(client);
        return client;
    }
}
