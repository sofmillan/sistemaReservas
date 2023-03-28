package com.example.workshopLHotelAshir;

import com.example.workshopLHotelAshir.model.Cliente;
import com.example.workshopLHotelAshir.repository.RepositoryCliente;
import com.example.workshopLHotelAshir.repository.RepositoryHabitacion;
import com.example.workshopLHotelAshir.repository.RepositoryReserva;
import com.example.workshopLHotelAshir.service.ServiceCliente;
import com.example.workshopLHotelAshir.service.ServiceReserva;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ClienteServiceTest {
    RepositoryCliente clienteRepository;
    ServiceCliente clienteService;
    @Before
    public void setUp(){
        this.clienteRepository = mock(RepositoryCliente.class);
        this.clienteService = new ServiceCliente(clienteRepository);
    }

    @Test(expected=RuntimeException.class)
    public void pruebaNombreNulo(){
        Cliente cliente = new Cliente(123L,null,"Millan","Cll 26","17","s@gmail.com");
        Cliente clienteCreado = this.clienteService.crear(cliente);
        verify(clienteRepository, times(1)).save(clienteCreado);
    }

    @Test(expected=RuntimeException.class)
    public void pruebaApellidoNulo(){
        Cliente cliente = new Cliente(123L,"Sofía",null,"Cll 26","17","s@gmail.com");
        Cliente clienteCreado = this.clienteService.crear(cliente);
        verify(clienteRepository, times(1)).save(clienteCreado);
    }
    @Test
    public void pruebaDatosCompletos(){
        Cliente cliente = new Cliente(123L,"Sofía","Millán","Cll 26","17","s@gmail.com");
        Cliente clienteCreado = this.clienteService.crear(cliente);
        verify(clienteRepository, times(1)).save(clienteCreado);
    }
}
