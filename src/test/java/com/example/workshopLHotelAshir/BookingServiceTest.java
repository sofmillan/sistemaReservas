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

    BookingRepository bookingRepository;
    RoomRepository roomRepository;
    ClientRepository clientRepository;
    BookingService bookingService;

    @Before
    public void setUp(){
        this.bookingRepository = mock(BookingRepository.class);
        this.clientRepository = mock(ClientRepository.class);
        this.roomRepository = mock(RoomRepository.class);
        this.bookingService = new BookingService(roomRepository, clientRepository, bookingRepository);
    }

    @Test(expected= InvalidDataException.class)
    public void pruebaReservaConFechaNula(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String date = null;
        BookingConfirmationDTO confirmation = this.bookingService.book(idClient,roomNumber,date);
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_BookingDatePriorToCurrentDate(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clientRepository.findById(any())).thenReturn(Optional.of(client));
        when(roomRepository.findById(any())).thenReturn(Optional.of(room));
        BookingConfirmationDTO confirmation = this.bookingService.book(idClient,roomNumber,bookingDate);
        verify(bookingRepository,times(1)).save(any());
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_BookingDateInvalidFormat(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "5-5-2023";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clientRepository.findById(any())).thenReturn(Optional.of(client));
        when(roomRepository.findById(any())).thenReturn(Optional.of(room));
        BookingConfirmationDTO confirmation = this.bookingService.book(idClient,roomNumber,bookingDate);
        verify(bookingRepository,times(1)).save(any());
    }


    @Test(expected = DataNotFoundException.class)
    public void Should_ThrowException_WhenRoomNumberNotFound(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingConfirmationDTO confirmation = this.bookingService.book(idClient,roomNumber,bookingDate);
        when(clientRepository.findById(any())).thenReturn(Optional.of(client));
        when(roomRepository.findById(any())).thenReturn(Optional.empty());
        Booking booking = new Booking(bookingDate, client, room);
        assertNull(booking.getClient());
        assertNull(booking.getRoom());
    }

    @Test(expected = DataNotFoundException.class)
    public void Should_ThrowException_WhenIdClientNotFound(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2010-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingConfirmationDTO confirmation = this.bookingService.book(idClient,roomNumber,bookingDate);
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        when(roomRepository.findById(any())).thenReturn(Optional.of(room));
        Booking booking = new Booking(bookingDate, client, room);
        assertNull(booking.getClient());
        assertNull(booking.getRoom());
    }

    @Test(expected = DataNotFoundException.class)
    public void Should_ThrowException_WhenIdClientAndRoomNumberNotFound(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2023-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        BookingConfirmationDTO confirmation = this.bookingService.book(idClient,roomNumber,bookingDate);
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        when(roomRepository.findById(any())).thenReturn(Optional.empty());
        Booking booking = new Booking(bookingDate, client, room);
        assertNull(booking.getClient());
        assertNull(booking.getRoom());
    }

    @Test
    public void BookingTest(){
        Long idClient = 123L;
        Integer roomNumber = 101;
        String bookingDate = "2023-05-05";
        Client client = new Client(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Room room = new Room(101,"premium",100000.0);
        when(clientRepository.findById(idClient)).thenReturn(Optional.of(client));
        when(roomRepository.findById(any())).thenReturn(Optional.of(room));
        BookingConfirmationDTO confirmation = this.bookingService.book(idClient,roomNumber,bookingDate);
        Booking booking = new Booking(bookingDate, client, room);
        verify(bookingRepository,times(1)).save(any());
        assertEquals(123L, (long) booking.getClient().getClientId());
        assertEquals(101, (int) booking.getRoom().getRoomNumber());
        assertNotNull(booking.getBookingDate());
    }
}
