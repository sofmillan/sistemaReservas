package com.example.workshopLHotelAshir.repository;

import com.example.workshopLHotelAshir.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    @Query("select r from  Room r")
    Set<Room> getAllRooms();
}
