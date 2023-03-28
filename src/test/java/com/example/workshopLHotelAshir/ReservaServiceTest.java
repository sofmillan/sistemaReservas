package com.example.workshopLHotelAshir;

import com.example.workshopLHotelAshir.model.Cliente;
import com.example.workshopLHotelAshir.model.Confirmacion;
import com.example.workshopLHotelAshir.model.Habitacion;
import com.example.workshopLHotelAshir.repository.RepositoryCliente;
import com.example.workshopLHotelAshir.repository.RepositoryHabitacion;
import com.example.workshopLHotelAshir.repository.RepositoryReserva;

import com.example.workshopLHotelAshir.service.ServiceReserva;
import org.junit.*;

import java.util.Optional;

import static org.mockito.Mockito.*;


public class ReservaServiceTest {

    RepositoryReserva reservaRepository;
    RepositoryHabitacion habitacionRepository;
    RepositoryCliente clienteRepository;
    ServiceReserva reservaService;

    @Before
    public void setUp(){
        this.reservaRepository = mock(RepositoryReserva.class);
        this.clienteRepository = mock(RepositoryCliente.class);
        this.habitacionRepository = mock(RepositoryHabitacion.class);
        this.reservaService = new ServiceReserva(habitacionRepository,clienteRepository,reservaRepository);
    }

    @Test(expected=RuntimeException.class)
    public void pruebaReservaConFechaNula(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = null;
        Confirmacion confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }

    @Test(expected=RuntimeException.class)
    public void pruebaReservaConCedulaNegativa(){
        Long cedula = -123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        Confirmacion confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }

    @Test(expected=RuntimeException.class)
    public void pruebaReservaConNumeroHabitacionNegativo(){
        Long cedula = 123L;
        Integer numero = -101;
        String fecha = "2023-05-05";
        Confirmacion confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }
    @Test(expected=RuntimeException.class)
    public void pruebaReservaFechaAnteriorActual(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2010-05-05";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        Confirmacion confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        verify(reservaRepository,times(1)).save(any());
    }

    @Test
    public void pruebaReservaHabitacion(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        Confirmacion confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        verify(reservaRepository,times(1)).save(any());
    }
}
