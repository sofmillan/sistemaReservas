package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/cliente")
    public ResponseEntity<Client> crear(@RequestBody Client client){
        Client client1 = clientService.crear(client);
        return ResponseEntity.ok(client1);
    }
}
