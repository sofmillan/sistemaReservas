package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.dto.BookingConfirmationDTO;
import com.example.workshopLHotelAshir.dto.BookingsByClientDTO;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/client/{idClient}/room/{roomNumber}/date/{bookingDate}/book")
    public ResponseEntity<BookingConfirmationDTO> add(@PathVariable ("idClient") Long idClient,
                                                      @PathVariable ("roomNumber") Integer roomNumber,
                                                      @PathVariable ("bookingDate") String bookingDate){
        BookingConfirmationDTO confirmation = bookingService.book(idClient, roomNumber, bookingDate);
        return ResponseEntity.ok(confirmation);
    }


    @GetMapping("/client/{idClient}")
    public List<BookingsByClientDTO> getByClient(@PathVariable Long idClient){
        return this.bookingService.getByClient(idClient);
    }

    @GetMapping("/availableByDate")
    public Set<Room> getByDate(@RequestParam String date){
        return this.bookingService.getByDate(date);
    }
    @GetMapping("/availableByDateType")
    public Set<Room> getByDateType(@RequestParam String date, @RequestParam String type){
        return this.bookingService.getByDateType(date,type);
    }
}
