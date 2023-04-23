package com.example.workshopLHotelAshir;

import com.example.workshopLHotelAshir.dto.BookingConfirmationDTO;
import com.example.workshopLHotelAshir.exceptions.DataNotFoundException;
import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.model.Booking;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import com.example.workshopLHotelAshir.repository.RoomRepository;
import com.example.workshopLHotelAshir.repository.BookingRepository;

import com.example.workshopLHotelAshir.service.BookingService;
import org.junit.*;

import java.util.Optional;

import static org.junit.Assert.*;
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
        Long idClient = 123L;
        Integer roomNumber = 101;
        String fecha = null;
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,fecha);
    }

 /*   @Test(expected=InvalidDataException.class)
    public void pruebaReservaConCedulaNegativa(){
        Long idClient = -123L;
        Integer roomNumber = 101;
        String bookingDate = "2023-05-05";
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,bookingDate);
    }

    @Test(expected=InvalidDataException.class)
    public void pruebaReservaConNumeroHabitacionNegativo(){
        Long idClient = 123L;
        Integer roomNumber = -101;
        String bookingDate = "2023-05-05";
        BookingConfirmationDTO confirmation = this.reservaService.book(idClient,roomNumber,bookingDate);
    }*/
    @Test(expected= InvalidDataException.class)
    public void pruebaReservaFechaAnteriorActual(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,bookingDate);
        verify(reservaRepository,times(1)).save(any());
    }

    @Test(expected= InvalidDataException.class)
    public void pruebaReservaFechaFormatoIncorrecto(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "5-5-2023";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,bookingDate);
        verify(reservaRepository,times(1)).save(any());
    }


    @Test(expected = DataNotFoundException.class)
    public void pruebaHabitacionNoEncontrada(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,bookingDate);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        Booking booking = new Booking(bookingDate, client, room);
        assertNull(booking.getClient());
        assertNull(booking.getRoom());
    }

    @Test(expected = DataNotFoundException.class)
    public void pruebaClienteNoEncontrado(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,bookingDate);
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        Booking booking = new Booking(bookingDate, client, room);
        assertNull(booking.getClient());
        assertNull(booking.getRoom());
    }

    @Test(expected = DataNotFoundException.class)
    public void pruebaClienteNiHabitacionEncontrados(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2023-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,bookingDate);
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        Booking booking = new Booking(bookingDate, client, room);
        assertNull(booking.getClient());
        assertNull(booking.getRoom());
    }

    @Test
    public void pruebaReservaHabitacion(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2023-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clienteRepository.findById(idClient)).thenReturn(Optional.of(client));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(room));
        BookingConfirmationDTO confirmacion = this.reservaService.book(idClient,roomNumber,bookingDate);
        Booking booking = new Booking(bookingDate, client, room);
        verify(reservaRepository,times(1)).save(any());
        assertEquals(123L, (long) booking.getClient().getClientId());
        assertEquals(101, (int) booking.getRoom().getRoomNumber());
        assertNotNull(booking.getBookingDate());
    }
}
