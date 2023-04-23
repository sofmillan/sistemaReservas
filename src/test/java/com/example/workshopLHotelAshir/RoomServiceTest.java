package com.example.workshopLHotelAshir;

import com.example.workshopLHotelAshir.exceptions.DataAlreadyExistsException;
import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.repository.BookingRepository;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import com.example.workshopLHotelAshir.repository.RoomRepository;
import com.example.workshopLHotelAshir.service.BookingService;
import com.example.workshopLHotelAshir.service.RoomService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RoomServiceTest {

    RoomRepository roomRepository;
    RoomService roomService;

    @Before
    public void setUp(){
        this.roomRepository = mock(RoomRepository.class);
        this.roomService = new RoomService(roomRepository);
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_IdIsNull(){
        Room room = new Room(null,"premium",100000.0);
        Room registeredRoom = this.roomService.registerRoom(room);
        verify(roomRepository, times(1)).save(room);
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_TypeIsNull(){
        Room room = new Room(101,null,100000.0);
        Room registeredRoom = this.roomService.registerRoom(room);
        verify(roomRepository, times(1)).save(room);
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_BasePriceIsNull(){
        Room room = new Room(101,"premium",null);
        Room registeredRoom = this.roomService.registerRoom(room);
        verify(roomRepository, times(1)).save(room);
    }

    @Test(expected= InvalidDataException.class)
    public void Should_ThrowException_When_NegativeRoomNumber(){
        Room room = new Room(101,"premium",null);
        Room registeredRoom = this.roomService.registerRoom(room);
        verify(roomRepository, times(1)).save(room);
    }

    @Test(expected = DataAlreadyExistsException.class)
    public void Should_ThrowException_When_IdExists(){

        Room room1 = new Room(101,"premium",100000.0);
        Room room2 = new Room(101,"premium",90000.0);
        when(roomRepository.save(room2)).thenThrow(DataAlreadyExistsException.class);

        Room createdRoom1 = this.roomService.registerRoom(room1);
        Room createdRoom2 = this.roomService.registerRoom(room2);

        verify(roomRepository.save(createdRoom2));
    }
}
