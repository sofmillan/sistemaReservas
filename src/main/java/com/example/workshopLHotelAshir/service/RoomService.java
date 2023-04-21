package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room crear(Room room){
        if(room.getNumero()==null|| room.getTipoHabitacion()==null|| room.getPrecioBase()==null){
            throw new InvalidDataException(("Los datos de la habitación no pueden ser nulos"));
        }
        this.roomRepository.save(room);
        return room;
    }
}
