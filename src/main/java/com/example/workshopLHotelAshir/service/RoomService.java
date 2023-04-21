package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.exceptions.DataAlreadyExistsException;
import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room registerRoom(Room room){
        Optional<Room> optionalRoom = roomRepository.findById(room.getBookNumber());
        if(optionalRoom.isPresent()){
            throw new DataAlreadyExistsException("The room "+room.getBookNumber()+" is registered already.");
        }
        if(room.getBookNumber()==null){
            throw new InvalidDataException("The book number cannot be null");
        }
        if(room.getType()==null){
            throw new InvalidDataException("The room type cannot be null");
        }
        if(room.getBasePrice()==null){
            throw new InvalidDataException("The base price cannot be null");
        }
        return this.roomRepository.save(room);
    }
}
