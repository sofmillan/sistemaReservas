package com.example.workshopLHotelAshir;

import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import com.example.workshopLHotelAshir.service.ClientService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ClientServiceTest {
    ClientRepository clienteRepository;
    ClientService clienteService;
    @Before
    public void setUp(){
        this.clienteRepository = mock(ClientRepository.class);
        this.clienteService = new ClientService(clienteRepository);
    }

    @Test(expected= InvalidDataException.class)
    public void pruebaNombreNulo(){
        Client client = new Client(123L,null,"Millan","Cll 26","17","s@gmail.com");
        Client clientCreado = this.clienteService.crear(client);
        verify(clienteRepository, times(1)).save(clientCreado);
    }

    @Test(expected=InvalidDataException.class)
    public void pruebaApellidoNulo(){
        Client client = new Client(123L,"Sofía",null,"Cll 26","17","s@gmail.com");
        Client clientCreado = this.clienteService.crear(client);
        verify(clienteRepository, times(1)).save(clientCreado);
    }
    @Test
    public void pruebaDatosCompletos(){
        Client client = new Client(123L,"Sofía","Millán","Cll 26","17","s@gmail.com");
        Client clientCreado = this.clienteService.crear(client);
        verify(clienteRepository, times(1)).save(clientCreado);
    }
}
