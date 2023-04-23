package com.example.workshopLHotelAshir;

import com.example.workshopLHotelAshir.exceptions.DataAlreadyExistsException;
import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import com.example.workshopLHotelAshir.service.ClientService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ClientServiceTest {
    ClientRepository clientRepository;
    ClientService clientService;
    @Before
    public void setUp(){
        this.clientRepository = mock(ClientRepository.class);
        this.clientService = new ClientService(clientRepository);
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_IdIsNull(){
        Client client = new Client(null,"Sofia","Millan","Cll 29","17","s@gmail.com");
        Client clientCreado = this.clientService.addClient(client);
        verify(clientRepository, times(1)).save(client);
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_NegativeId(){
        Client client = new Client(-123L,"Sofia","Millan","Cll 29","17","s@gmail.com");
        Client clientCreado = this.clientService.addClient(client);
        verify(clientRepository, times(1)).save(client);
    }
    @Test(expected = DataAlreadyExistsException.class)
    public void Should_ThrowException_When_IdExists(){

        Client client1 = new Client(1L,"Sofia","Millan","Cll26","17",
                "sofia@gmail.com");
        Client client2 = new Client(1L,"Isabella","Millan","Cll86","17",
                "isabella@gmail.com");
        when(clientRepository.save(client2)).thenThrow(DataAlreadyExistsException.class);

        Client createdClient1 = this.clientService.addClient(client1);
        Client createdClient2 = this.clientService.addClient(client2);

        verify(clientRepository.save(createdClient2));
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_NameIsNull(){
        Client client = new Client(123L,null,"Millan","Cll 26","17","s@gmail.com");
        Client clientCreado = this.clientService.addClient(client);
        verify(clientRepository, times(1)).save(client);
    }

    @Test(expected=InvalidDataException.class)
    public void Should_ThrowException_When_LastNameIsNull(){
        Client client = new Client(123L,"Sofía",null,"Cll 26","17","s@gmail.com");
        Client clientCreado = this.clientService.addClient(client);
        verify(clientRepository, times(1)).save(client);
    }
    @Test
    public void CreateClientTest(){
        Client client = new Client(123L,"Sofía","Millán","Cll 26","17","s@gmail.com");
        Client clientCreado = this.clientService.addClient(client);
        verify(clientRepository).save(client);
    }
}
