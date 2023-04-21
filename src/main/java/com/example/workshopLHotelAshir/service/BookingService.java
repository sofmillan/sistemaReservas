package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.dto.BookingDTO;
import com.example.workshopLHotelAshir.exceptions.*;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.model.Booking;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import com.example.workshopLHotelAshir.repository.RoomRepository;
import com.example.workshopLHotelAshir.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookingService {
    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(RoomRepository roomRepository, ClientRepository clientRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.clientRepository = clientRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingDTO reservar(Long clientId, Integer roomNumber, String bookingDate){
        if(clientId == null){
            throw new InvalidDataException("Client id cannot be null");
        }
        if(roomNumber == null){
            throw new InvalidDataException("Room number cannot be null");
        }
        if (bookingDate == null){
            throw new InvalidDataException("Booking date cannot be null");
        }

        Optional<Client> optionalClient = this.clientRepository.findById(clientId);
        Optional<Room> optionalRoom = this.roomRepository.findById(roomNumber);
        if(optionalRoom.isEmpty()){
            throw new DataNotFoundException("Room with number "+roomNumber+" is not registered");
        }
        if(optionalClient.isEmpty()){
            throw new DataNotFoundException("Client with id "+clientId+" is not registered");
        }

        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = pattern.matcher(bookingDate);

        if(!matcher.find()){
                throw new InvalidDataException("The date format is not valid. It must be yyyy-MM-dd");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(bookingDate, formatter);

        if(date.isBefore(LocalDate.now())){
            throw new InvalidDataException("The booking date cannot be prior to today's date.");
        }

        List<Integer> roomsAvailable = this.bookingRepository.getAvailability(bookingDate);
        boolean isAvailable = roomsAvailable.contains(roomNumber);
        boolean noBookings = this.bookingRepository.cantidadReservas()==0;

        if(!(noBookings||(roomsAvailable.size()!=0 && isAvailable))){
            throw new BookedRoomException("The room "+roomNumber+" is already booked for "+date);
        }
            Room room = optionalRoom.get();
            double discount = 0;
            Booking booking = new Booking(optionalClient.get(),optionalRoom.get(),bookingDate);
            if(room.getType().equalsIgnoreCase("premium")){
                discount = room.getBasePrice() * 0.05;
            }
            booking.setTotal(room.getBasePrice() - discount);
            this.bookingRepository.save(booking);

            return new BookingDTO(booking.getCodigo(), booking.getFechaReserva(), booking.getHabitacion().getBookNumber(), booking.getTotal());

    }

    public List<Booking> getByClient(Long cedula){
        return this.bookingRepository.findAllById(cedula);
    }

    public Set<Room> getByDate(String date) {
        return this.bookingRepository.findByDate(date);
    }

    public List<Room> getByDateType(String date, String tipo){
        return this.bookingRepository.findByDateType(date, tipo);
    }
}
