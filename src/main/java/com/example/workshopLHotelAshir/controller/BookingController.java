package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.dto.BookingDTO;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.model.Booking;
import com.example.workshopLHotelAshir.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1")
public class BookingController {
    private BookingService reservaService;

    @Autowired
    public BookingController(BookingService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/cliente/{cedula}/habitacion/{numero}/fecha/{fechaReserva}/reservar")
    public ResponseEntity<BookingDTO> add(@PathVariable ("cedula") Long cedula,
                                          @PathVariable ("numero") Integer numero,
                                          @PathVariable ("fechaReserva") String fechaReserva){
        BookingDTO confirmacion = reservaService.reservar(cedula, numero, fechaReserva);
        return ResponseEntity.ok(confirmacion);
    }


    @GetMapping("/cliente/{cedula}")
    public List<Booking> getByClient(@PathVariable Long cedula){
        return this.reservaService.getByClient(cedula);
    }

    @GetMapping("/disponible-fecha")
    public Set<Room> getByDate(@RequestParam String fecha){
        return this.reservaService.getByDate(fecha);
    }
    @GetMapping("/disponible-fecha-tipo")
    public List<Room> getByDateType(@RequestParam String fecha, @RequestParam String tipo){
        return this.reservaService.getByDateType(fecha,tipo);
    }
}
