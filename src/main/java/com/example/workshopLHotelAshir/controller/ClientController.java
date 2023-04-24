package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="Client")
@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Create a new client")
    @ApiResponses( value= {
            @ApiResponse(code = 200, message = "Client created successfully"),
            @ApiResponse(code = 400, message = "Data is not valid, check the input"),
            @ApiResponse(code = 409, message = "Client already exists, check the id"),
    })
    @PostMapping("/client")
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client createdClient = clientService.addClient(client);
        return ResponseEntity.ok(createdClient);
    }
}
