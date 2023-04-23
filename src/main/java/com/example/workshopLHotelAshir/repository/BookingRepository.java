package com.example.workshopLHotelAshir.repository;

import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.model.Booking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface BookingRepository extends CrudRepository<Booking, UUID> {
    @Query("SELECT b FROM Booking b WHERE b.client.clientId = ?1")
    List<Booking> findAllById(Long id);

    @Query("Select r FROM Room r, Booking b WHERE r.roomNumber not in(SELECT room.roomNumber from Booking) " +
            "OR r.roomNumber not in(select distinct b.room.roomNumber from  Booking b where b.bookingDate= ?1)")
    Set<Room> findByDate(String date);

    @Query("Select r FROM Room r, Booking b WHERE (r.roomNumber  not in(SELECT room.roomNumber from Booking) " +
            "OR r.roomNumber not in(select distinct b.room.roomNumber from  Booking b where b.bookingDate = ?1)) AND r.type=?2")
    Set<Room> findByDateType(String date, String type);

    @Query("Select r.roomNumber FROM Room r, Booking b WHERE r.roomNumber not in(SELECT room.roomNumber from Booking) " +
            "OR r.roomNumber not in(select distinct b.room.roomNumber from  Booking b where b.bookingDate= ?1)")
    List<Integer> getAvailability(String date);

    @Query("select count(*) from Booking")
    Integer getAmountBookings();


}

