package com.example.workshopLHotelAshir;

import com.example.workshopLHotelAshir.dto.BookingDTO;
import com.example.workshopLHotelAshir.exceptions.DataNotFoundException;
import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.model.Booking;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import com.example.workshopLHotelAshir.repository.RoomRepository;
import com.example.workshopLHotelAshir.repository.BookingRepository;
import static org.junit.Assert.assertTrue;
import com.example.workshopLHotelAshir.service.BookingService;
import org.junit.*;

import java.util.Optional;

import static org.mockito.Mockito.*;


public class BookingServiceTest {

    BookingRepository reservaRepository;
    RoomRepository habitacionRepository;
    ClientRepository clienteRepository;
    BookingService reservaService;

    @Before
    public void setUp(){
        this.reservaRepository = mock(BookingRepository.class);
        this.clienteRepository = mock(ClientRepository.class);
        this.habitacionRepository = mock(RoomRepository.class);
        this.reservaService = new BookingService(habitacionRepository,clienteRepository,reservaRepository);
    }

    @Test(expected= InvalidDataException.class)
    public void pruebaReservaConFechaNula(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = null;
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }

    @Test(expected=InvalidDataException.class)
    public void pruebaReservaConCedulaNegativa(){
        Long cedula = -123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }

    @Test(expected=InvalidDataException.class)
    public void pruebaReservaConNumeroHabitacionNegativo(){
        Long cedula = 123L;
        Integer numero = -101;
        String fecha = "2023-05-05";
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }
    @Test(expected= InvalidDataException.class)
    public void pruebaReservaFechaAnteriorActual(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        verify(reservaRepository,times(1)).save(any());
    }

    @Test(expected= InvalidDataException.class)
    public void pruebaReservaFechaFormatoIncorrecto(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "5-5-2023";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        verify(reservaRepository,times(1)).save(any());
    }


    @Test(expected = DataNotFoundException.class)
    public void pruebaHabitacionNoEncontrada(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        Booking booking = new Booking(client, room,fecha);
        assertTrue(booking.getCliente() == null);
        assertTrue(booking.getHabitacion() == null);
    }

    @Test(expected = DataNotFoundException.class)
    public void pruebaClienteNoEncontrado(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        Booking booking = new Booking(client, room,fecha);
        assertTrue(booking.getCliente() == null);
        assertTrue(booking.getHabitacion() == null);
    }

    @Test(expected = DataNotFoundException.class)
    public void pruebaClienteNiHabitacionEncontrados(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        Booking booking = new Booking(client, room,fecha);
        assertTrue(booking.getCliente() == null);
        assertTrue(booking.getHabitacion() == null);
    }

    @Test
    public void pruebaReservaHabitacion(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clienteRepository.findById(cedula)).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        BookingDTO confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        Booking booking = new Booking(client, room,fecha);
        verify(reservaRepository,times(1)).save(any());
        assertTrue(booking.getCliente().getCedula()==123L);
        assertTrue(booking.getHabitacion().getNumero()==101);
        assertTrue(booking.getFechaReserva()!=null);
    }
}
