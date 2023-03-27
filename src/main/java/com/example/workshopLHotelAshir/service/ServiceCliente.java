package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.model.Cliente;
import com.example.workshopLHotelAshir.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCliente {

    private RepositoryCliente repositoryCliente;

    @Autowired
    public ServiceCliente(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    public Cliente crear(Cliente cliente){
        if(cliente.getApellido()==null||cliente.getNombre()==null){

            throw new RuntimeException(("Nombre y apellido no pueden ser nulos"));
        }
        this.repositoryCliente.save(cliente);
        return cliente;
    }
}
