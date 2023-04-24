package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.dto.BookingConfirmationDTO;
import com.example.workshopLHotelAshir.dto.BookingByClientDTO;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api(tags="Booking")
@RestController
@RequestMapping("api/v1")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @ApiOperation(value = "Book a room for a day")
    @ApiResponses( value= {
            @ApiResponse(code = 200, message = "Room booked successfully"),
            @ApiResponse(code = 400, message = "Data is not valid, check the input"),
            @ApiResponse(code = 409, message = "This room is already booked, try another date"),
            @ApiResponse(code = 500, message = "Some data was not found")
    })
    @PostMapping("/client/{idClient}/room/{roomNumber}/date/{bookingDate}/book")
    public ResponseEntity<BookingConfirmationDTO> book(@PathVariable ("idClient") Long idClient,
                                                      @PathVariable ("roomNumber") Integer roomNumber,
                                                      @PathVariable ("bookingDate") String bookingDate){
        BookingConfirmationDTO confirmation = bookingService.book(idClient, roomNumber, bookingDate);
        return ResponseEntity.ok(confirmation);
    }

    @ApiOperation(value = "Get all the reservations from a specific client")
    @ApiResponses( value= {
            @ApiResponse(code = 200, message = "The information is brought"),
            @ApiResponse(code = 409, message = "Some data was not found")
    })
    @GetMapping("/client/{idClient}")
    public List<BookingByClientDTO> getByClient(@PathVariable Long idClient){
        return this.bookingService.getByClient(idClient);
    }

    @ApiOperation(value = "Get the available rooms by a specific date")
    @ApiResponses( value= {
            @ApiResponse(code = 200, message = "The information is brought"),
            @ApiResponse(code = 409, message = "Some data was not found")
    })
    @GetMapping("/availableByDate")
    public Set<Room> getByDate(@RequestParam String date){
        return this.bookingService.getByDate(date);
    }

    @ApiOperation(value = "Get the available rooms by a specific date and type")
    @ApiResponses( value= {
            @ApiResponse(code = 200, message = "The information is brought"),
            @ApiResponse(code = 409, message = "Some data was not found")
    })
    @GetMapping("/availableByDateType")
    public Set<Room> getByDateType(@RequestParam String date, @RequestParam String type){
        return this.bookingService.getByDateType(date,type);
    }
}
